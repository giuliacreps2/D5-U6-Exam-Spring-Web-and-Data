package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories;

import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Booking;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, UUID> {
    boolean existsByEmployeeAndTravelTravelDate(Employee employee, LocalDate travelDate);


}
