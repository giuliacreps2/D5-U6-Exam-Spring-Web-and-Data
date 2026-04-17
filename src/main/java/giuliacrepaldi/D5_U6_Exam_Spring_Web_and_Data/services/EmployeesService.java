package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Employee;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.BadRequestException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.NotFoundException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.ValidationException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.EmployeeDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class EmployeesService {
    //Dipendenze
    private final EmployeesRepository employeesRepository;
    private final Cloudinary cloudinaryUploader;

    //Costruttore
    public EmployeesService(EmployeesRepository employeesRepository, Cloudinary cloudinaryUploader) {
        this.employeesRepository = employeesRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }


    //1.SAVE
    public Employee saveEmployee(EmployeeDTO body) {
        //Controllo che la mail non sia già in uso

        //Creo un nuovo utente
        Employee newEmployee = new Employee(body.name(), body.email());
        Employee savedEmployee = this.employeesRepository.save(newEmployee);

        log.info("L'utente con id: " + savedEmployee.getEmployeeId() + " e " + savedEmployee.getName() + "è stato salvato con successo");
        return savedEmployee;
    }


    //2.FINDBYID
    public Employee findById(UUID employeeId) {
        return this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }


//    //3.FINDALLUSERS
//    public List<User> findAll(User user) {
//        return this.usersRepository.findAll();
//    }

    //3.UPDATE
    public Employee findByIdAndUpdate(UUID employeeId, EmployeeDTO body) {
        Employee found = this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));

        //Controllo che la mail sia in uso e che questo controllo avvenga solo se sta effettivamente cambiando email
        if (!found.getEmail().equals(body.email())) {
            if (this.employeesRepository.existsByEmail(body.email()))
                throw new BadRequestException(("L'indirizzo email " + body.email() + " è già in uso!"));
        }

        //Modifico l'utente trovato
        found.setName(body.name());
        found.setEmail(body.email());

        //Salvo le modifiche
        Employee updateEmployee = this.employeesRepository.save(found);

        //Log
        log.info("L'utente " + updateEmployee.getEmployeeId() + " è stato modificato correttamente");

        return updateEmployee;
    }


    //4.DELETE
    public void findByIdAndDelete(UUID employeeId) {
        Employee found = this.employeesRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
        this.employeesRepository.delete(found);
    }


    //5. UPLOAD Immagini
    public void avatarUpload(MultipartFile file, UUID employeeId) {
        //Controllo se il file è più grande di un tot
        if (file.getContentType() == null || !file.getContentType().equals("image/jpeg") || file.isEmpty())
            throw new ValidationException("Il formato del file non è valido");
        if (file.getSize() > 2 * 1024 * 1024)
            throw new ValidationException("Il file non deve superare i 2 MB");

        //Devo fare il find by id dell'utente
        Employee found = findById(employeeId);
        //3.Upload del file
        try {
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            log.info(url.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //6.PAGINE
    public Page<Employee> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.employeesRepository.findAll(pageable);
    }
}

