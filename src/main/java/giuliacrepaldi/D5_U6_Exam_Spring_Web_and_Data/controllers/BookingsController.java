package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.controllers;


import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.entities.Booking;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions.ValidationException;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.BookingDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads.NewBookingRespDTO;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.BookingService;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.EmployeesService;
import giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.services.TravelsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingsController {
    //Dipendenze
    private final BookingService bookingService;
    private final EmployeesService employeesService;
    private final TravelsService travelsService;

    //Costruttore
    public BookingsController(BookingService bookingService, EmployeesService employeesService, TravelsService travelsService) {
        this.bookingService = bookingService;
        this.employeesService = employeesService;
        this.travelsService = travelsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewBookingRespDTO saveBooking(@RequestBody @Validated BookingDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        }
        Booking newBooking = this.bookingService.saveBooking(body);
        return new NewBookingRespDTO(newBooking.getBookingId());
    }


    //    //2.2 GET (findByEmployeeId)
//    @GetMapping("/{employyeId}")
//    public List<Travel> findAllByEmployee(@PathVariable UUID employyeId) {
//        return this.travelsService.findByEmployeeId(employyeId);
//    }


}
