package exceptions.centre;

public class NomDejaPresentException extends Exception {
    public NomDejaPresentException() {
    }

    public NomDejaPresentException(String message) {
        super(message);
    }

    public NomDejaPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NomDejaPresentException(Throwable cause) {
        super(cause);
    }

    public NomDejaPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
