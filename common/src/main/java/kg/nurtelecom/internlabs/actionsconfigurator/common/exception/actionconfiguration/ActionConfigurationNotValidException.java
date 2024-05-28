package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.actionconfiguration;

public class ActionConfigurationNotValidException extends RuntimeException {

    public ActionConfigurationNotValidException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ActionConfigurationNotValidException(String message) {
        super(message);
    }

    public ActionConfigurationNotValidException(Throwable throwable) {
        super(throwable);
    }

}
