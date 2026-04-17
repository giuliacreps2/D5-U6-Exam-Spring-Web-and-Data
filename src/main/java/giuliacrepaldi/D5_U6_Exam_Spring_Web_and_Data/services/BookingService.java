package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services;


import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Booking;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.BookingDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.EmployeesRepository;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.TravelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {
    //Dipendenze
    private final TravelsRepository travelsRepository;
    private final EmployeesRepository employeesRepository;

    //Costruttore
    public BookingService(TravelsRepository travelsRepository, EmployeesRepository employeesRepository) {
        this.travelsRepository = travelsRepository;
        this.employeesRepository = employeesRepository;
    }

    //    1.SAVE
    public Booking saveBooking(BookingDTO bookingDTO) {
        //Controllo che la prenotazione non sia null
        //Controllo che employyeId e travelId esistano nel DB
        //Controllo che un dipendente abbia una sola prenotazione
        //per quella speficica data
        

    }
}
