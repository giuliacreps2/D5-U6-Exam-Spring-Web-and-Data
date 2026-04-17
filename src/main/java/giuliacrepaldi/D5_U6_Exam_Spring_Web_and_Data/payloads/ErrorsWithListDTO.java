package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(String message, LocalDateTime timestamp, List<String> errors) {
}
