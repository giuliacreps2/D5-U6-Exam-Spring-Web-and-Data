package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services;


import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Booking;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Employee;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Travel;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.BadRequestException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.NotFoundException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.BookingDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.BookingsRepository;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.EmployeesRepository;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.repositories.TravelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class BookingService {
    //Dipendenze
    private final TravelsRepository travelsRepository;
    private final EmployeesRepository employeesRepository;
    private final BookingsRepository bookingsRepository;

    //Costruttore
    public BookingService(TravelsRepository travelsRepository, EmployeesRepository employeesRepository, BookingsRepository bookingsRepository) {
        this.travelsRepository = travelsRepository;
        this.employeesRepository = employeesRepository;
        this.bookingsRepository = bookingsRepository;
    }

    //    1.SAVE
    public Booking saveBooking(BookingDTO bookingDTO) {
        //Controllo che la prenotazione non sia null
        if (bookingDTO == null) throw new BadRequestException("Booking DTO non esiste");
        //Controllo che employyeId e travelId esistano nel DB
        Employee foundEmployee = this.employeesRepository.findById(bookingDTO.employeeId())
                .orElseThrow(() -> new NotFoundException(bookingDTO.employeeId()));
        Travel foundTravel = this.travelsRepository.findById(bookingDTO.travelId())
                .orElseThrow(() -> new NotFoundException(bookingDTO.travelId()));
        //Controllo che la data non sia passata
        if (bookingDTO.bookingDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("La data non può essere nel passato");
        }
        //Controllo che un dipendente abbia una sola prenotazione
        //per quella specifica data
        if (bookingsRepository.existsByEmployeeAndTravelTravelDate(foundEmployee, foundTravel.getTravelDate())) {
            throw new BadRequestException("Il dipende ha già una prenotazione per quella data");
        }

        Booking newBooking = new Booking(bookingDTO.bookingDate(), bookingDTO.notes(), foundEmployee, foundTravel);
        log.info("La prenotazione è avvenuta con successo: " + newBooking.toString());
        return newBooking;
    }


//
////    //3.FINDBYEMPLOYEEID
////    public List<Travel> findAllByEmployeeId(UUID employeeId) {
////        return this.travelsRepository.findAllByEmployeeId(employeeId);
////    }
//
//    public List<Booking> findAllBookingsByEmployeeId(UUID employeeId) {
//        return
//    }

    //FIND ALL PRENOTAZIONI per utente
    //FIND ALL VIAGGI per utente
}
