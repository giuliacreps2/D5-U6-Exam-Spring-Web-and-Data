package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
