package kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user;

import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationException extends AuthenticationException {

    public UserAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAuthenticationException(String msg) {
        super(msg);
    }
}
