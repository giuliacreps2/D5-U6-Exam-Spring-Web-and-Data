package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
