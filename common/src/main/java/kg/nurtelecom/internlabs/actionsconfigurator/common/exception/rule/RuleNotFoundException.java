package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.rule;

public class RuleNotFoundException extends RuntimeException {

    public RuleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleNotFoundException(String message) {
        super(message);
    }
}
