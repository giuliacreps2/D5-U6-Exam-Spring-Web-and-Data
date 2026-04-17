package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDTO(
        @NotNull(message = "La data di viaggio non può essere vuota")
        LocalDate bookingDate,
        @Size(min = 10, max = 255, message = "Il numero di caratteri deve essere compresto tra un minimo di 10 e un massimo di 255 caratteri")
        String notes,
        @NotNull(message = "La prenotazione deve contenere l'id del dipendente")
        UUID employeeId,
        @NotNull(message = "La prenotazione deve comprendere l'id del viaggio")
        UUID travelId
) {
}
