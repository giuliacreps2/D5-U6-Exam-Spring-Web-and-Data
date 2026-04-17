package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.controllers;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Employee;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.ValidationException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.EmployeeDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.NewEmplRespDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeesController {
    //Dipendenze
    private final EmployeesService employeesService;

    //Costruttore
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }


    // 1. POST http://localhost:5430/employees (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewEmplRespDTO saveEmployee(@RequestBody @Validated EmployeeDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Employee newEmployee = this.employeesService.saveEmployee(body);
        return new NewEmplRespDTO(newEmployee.getEmployeeId());
    }


    //2. GET
    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable UUID employeeId) {
        return this.employeesService.findById(employeeId);
    }


    //3. PUT
    @PutMapping("/{employeeId}")
    public Employee getByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody EmployeeDTO body) {
        return this.employeesService.findByIdAndUpdate(employeeId, body);
    }

    //4.DELETE
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void getByIdAndDelete(@PathVariable UUID employeeId) {
        this.employeesService.findByIdAndDelete(employeeId);
    }

    //5. PATCH immagini/file
    @PatchMapping("/{employeeId}/avatar")
    public void uploadAvatar(@RequestParam("profile_picture") MultipartFile file, @PathVariable UUID employeeId) {

        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));
        log.info(file.getContentType());

        this.employeesService.avatarUpload(file, employeeId);
    }

    //6. GET PAGE
    @GetMapping
    public Page<Employee> getUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "surname") String sortBy) {
        return this.employeesService.findAll(page, size, sortBy);
    }
}
