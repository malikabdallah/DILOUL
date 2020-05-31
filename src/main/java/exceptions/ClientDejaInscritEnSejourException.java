package exceptions;

public class ClientDejaInscritEnSejourException extends Exception {
    public ClientDejaInscritEnSejourException() {
    }

    public ClientDejaInscritEnSejourException(String message) {
        super(message);
    }

    public ClientDejaInscritEnSejourException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientDejaInscritEnSejourException(Throwable cause) {
        super(cause);
    }

    public ClientDejaInscritEnSejourException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
