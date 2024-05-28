package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.stage;

public class StageNotFoundException extends RuntimeException {

    public StageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StageNotFoundException(String message) {
        super(message);
    }
}
