package rubberduke;

/**
 * Represents an exception caused by user error, to be caught and presented to the user.
 */
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }
}
