package kg.nurtelecom.internlabs.actionsconfigurator.common.exception;

public class MethodNotSupportedException extends RuntimeException {

    public MethodNotSupportedException() {
        this("Method not supported");
    }

    public MethodNotSupportedException(String message) {
        super(message);
    }
}
