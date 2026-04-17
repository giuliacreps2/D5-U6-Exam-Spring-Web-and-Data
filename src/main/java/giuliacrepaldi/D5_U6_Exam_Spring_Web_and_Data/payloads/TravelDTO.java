package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TravelDTO(
        @NotEmpty(message = "La destinazione non può essere vuota")
        @Size(min = 2, max = 50, message = "Il nome deve contenere un minimo di 2 e una massimo di 50 caratteri")
        String destination,
        @NotNull(message = "La data di viaggio non può essere vuota")
        LocalDate travelDate,
        @NotEmpty(message = "Il campo non può essere vuoto")
        String travelStatus
) {
}
