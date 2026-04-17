package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelsRepository extends JpaRepository<Travel, UUID> {
//    <T> ScopedValue<T> findByEmployeeId(UUID employeeId);
}
