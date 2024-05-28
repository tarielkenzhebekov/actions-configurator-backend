package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.promocode;

public class PromocodeNotFoundException extends RuntimeException {

    public PromocodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PromocodeNotFoundException(String message) {
        super(message);
    }
}
