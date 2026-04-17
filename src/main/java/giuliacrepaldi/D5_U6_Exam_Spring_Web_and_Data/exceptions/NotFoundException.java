package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}
