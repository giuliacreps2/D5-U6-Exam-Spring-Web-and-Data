package giuliacrepaldi.D5_U6_Exam_Spring_Web_and_Data.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(List<String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
