package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.controllers;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.User;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.ValidationException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.NewUserRespDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.UserDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.UsersService;
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
@RequestMapping("/users")
public class Controller {
    //Dipendenze
    private final UsersService usersService;

    //Costruttore
    public Controller(UsersService usersService) {
        this.usersService = usersService;
    }


    // 1. POST http://localhost:5430/users (+ req.body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated UserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        User newUser = this.usersService.saveUser(body);
        return new NewUserRespDTO(newUser.getUserId());
    }


    //2. GET
    @GetMapping("/{userId}")
    public User getById(@PathVariable UUID userId) {
        return this.usersService.findById(userId);
    }


    //3. PUT
    @PutMapping("/{userId}")
    public User getByIdAndUpdate(@PathVariable UUID userId, @RequestBody UserDTO body) {
        return this.usersService.findByIdAndUpdate(userId, body);
    }

    //4.DELETE
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void getByIdAndDelete(@PathVariable UUID userId) {
        this.usersService.findByIdAndDelete(userId);
    }

    //5. PATCH immagini/file
    @PatchMapping("/{userId}/avatar")
    public void uploadAvatar(@RequestParam("profile_picture") MultipartFile file, @PathVariable UUID userId) {

        log.info(file.getOriginalFilename());
        log.info(String.valueOf(file.getSize()));
        log.info(file.getContentType());

        this.usersService.avatarUpload(file, userId);
    }

    //6. GET PAGE
    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "surname") String sortBy) {
        return this.usersService.findAll(page, size, sortBy);
    }
}
