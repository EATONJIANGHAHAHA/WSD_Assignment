package exception;

/**
 * This class represents an exception that should be thrown when the validation of data fails.
 */
public class DataValidationException extends Exception {

    public DataValidationException(String message) {
        super(message);
    }
}
