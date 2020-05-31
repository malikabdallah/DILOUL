package exceptions;

public class ClientAgeNonValideException extends Exception {

    public ClientAgeNonValideException() {
    }

    public ClientAgeNonValideException(String message) {
        super(message);
    }

    public ClientAgeNonValideException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientAgeNonValideException(Throwable cause) {
        super(cause);
    }

    public ClientAgeNonValideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
