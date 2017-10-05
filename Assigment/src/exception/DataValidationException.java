package exception;

public class DataValidationException extends Exception {
    public DataValidationException() {
    }

    public DataValidationException(String message) {
        super(message);
    }
}
