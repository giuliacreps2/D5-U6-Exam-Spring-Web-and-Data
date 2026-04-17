package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
}
