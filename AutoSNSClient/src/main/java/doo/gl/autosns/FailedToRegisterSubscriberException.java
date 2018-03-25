package doo.gl.autosns;

public class FailedToRegisterSubscriberException extends RuntimeException {

    public FailedToRegisterSubscriberException(String message) {
        super(message);
    }

    public FailedToRegisterSubscriberException(String message, Throwable cause) {
        super(message, cause);
    }
}
