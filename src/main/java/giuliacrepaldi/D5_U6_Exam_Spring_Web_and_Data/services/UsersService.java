package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.User;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.BadRequestException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.NotFoundException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.ValidationException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.UserDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class UsersService {
    //Dipendenze
    private final UsersRepository usersRepository;
    private final Cloudinary cloudinaryUploader;

    //Costruttore
    public UsersService(UsersRepository usersRepository, Cloudinary cloudinaryUploader) {
        this.usersRepository = usersRepository;
        this.cloudinaryUploader = cloudinaryUploader;
    }


    //1.SAVE
    public User saveUser(UserDTO body) {
        //Controllo che la mail non sia già in uso

        //Creo un nuovo utente
        User newUser = new User(body.name(), body.email());
        User savedUser = this.usersRepository.save(newUser);

        log.info("L'utente con id: " + savedUser.getUserId() + " e " + savedUser.getName() + "è stato salvato con successo");
        return savedUser;
    }


    //2.FINDBYID
    public User findById(UUID userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }


//    //3.FINDALLUSERS
//    public List<User> findAll(User user) {
//        return this.usersRepository.findAll();
//    }

    //3.UPDATE
    public User findByIdAndUpdate(UUID userId, UserDTO body) {
        User found = this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));

        //Controllo che la mail sia in uso e che questo controllo avvenga solo se sta effettivamente cambiando email
        if (!found.getEmail().equals(body.email())) {
            if (this.usersRepository.existsByEmail(body.email()))
                throw new BadRequestException(("L'indirizzo email " + body.email() + " è già in uso!"));
        }

        //Modifico l'utente trovato
        found.setName(body.name());
        found.setEmail(body.email());

        //Salvo le modifiche
        User updateUser = this.usersRepository.save(found);

        //Log
        log.info("L'utente " + updateUser.getUserId() + " è stato modificato correttamente");

        return updateUser;
    }


    //4.DELETE
    public void findByIdAndDelete(UUID userId) {
        User found = this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        this.usersRepository.delete(found);
    }


    //5. UPLOAD Immagini
    public void avatarUpload(MultipartFile file, UUID userId) {
        //Controllo se il file è più grande di un tot
        if (file.getContentType() == null || !file.getContentType().equals("image/jpeg") || file.isEmpty())
            throw new ValidationException("Il formato del file non è valido");
        if (file.getSize() > 2 * 1024 * 1024)
            throw new ValidationException("Il file non deve superare i 2 MB");

        //Devo fare il find by id dell'utente
        User found = findById(userId);
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
    public Page<User> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.usersRepository.findAll(pageable);
    }
}

