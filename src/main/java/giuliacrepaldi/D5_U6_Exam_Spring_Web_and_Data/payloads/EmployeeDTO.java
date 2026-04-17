package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//TODO: completare i campi
public record EmployeeDTO(
        @NotBlank(message = "Il nome deve essere valido")
        @Size(min = 1, max = 30, message = "Il nome deve contenere un minimo di 2 e una massimo di 30 caratteri")
        String name,
        @NotBlank(message = "L'email è obbligatoria")
        @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Il formato mail deve essere valido")
        String email
) {
}
