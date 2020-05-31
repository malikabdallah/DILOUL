package exceptions.centre;

public class CapaciteException extends Exception {
    public CapaciteException() {
    }

    public CapaciteException(String message) {
        super(message);
    }

    public CapaciteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapaciteException(Throwable cause) {
        super(cause);
    }

    public CapaciteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
