package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.action;

public class ActionNotFoundException extends RuntimeException {

    public ActionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotFoundException(String message) {
        super(message);
    }
}
