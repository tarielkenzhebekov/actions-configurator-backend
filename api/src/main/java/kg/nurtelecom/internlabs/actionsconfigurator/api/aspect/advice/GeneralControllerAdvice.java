package kg.nurtelecom.internlabs.actionsconfigurator.api.aspect.advice;

import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action.ActionController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.actionconfiguration.ActionConfigurationManagerController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.auth.AuthenticationController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.promocode.PromocodeController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.rule.RuleController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.stage.StageController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.ticket.TicketController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.user.UserController;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.action.ActionNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.actionconfiguration.ActionConfigurationNotValidException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.promocode.PromocodeNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.rule.RuleNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.stage.StageNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.ticket.TicketNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserAuthenticationException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.user.UserNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The GeneralControllerAdvice class provides global exception handling and common
 * processing logic for all controllers in the application.
 */
@RestControllerAdvice(
        assignableTypes = {
                AuthenticationController.class,
                ActionController.class,
                RuleController.class,
                StageController.class,
                UserController.class,
                PromocodeController.class,
                TicketController.class,
                ActionConfigurationManagerController.class
        }
)
public class GeneralControllerAdvice {

    /**
     * Handles all not found exceptions for services.
     * @param e Exception that was caught.
     * @return Response with <code>ResultCode.FAIL</code> result code
     * and exception text message.
     */
    @ExceptionHandler({
            ActionNotFoundException.class,
            StageNotFoundException.class,
            RuleNotFoundException.class,
            TicketNotFoundException.class,
            PromocodeNotFoundException.class,
            UserNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage<?> handleNotFoundException(Exception e) {
        return new ResponseMessage<>(
                ResultCode.FAIL,
                e.getMessage()
        );
    }

    /**
     * Handles <code>MethodArgumentNotValidException</code> on request validation fail.
     * @param manve Exception that was caught.
     * @return Response message with <code>ResultCode.FAIL</code> result code
     * and list of validation text messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
        List<String> errorMessages = manve.getAllErrors()
                .stream()
                .map(error -> "Error: " + error.getDefaultMessage())
                .toList();

        Map<String, List<String>> errors = new HashMap<>();
        errors.put("Errors", errorMessages);

        return new ResponseMessage<>(
                errors,
                ResultCode.FAIL
        );
    }

    /**
     * Handles <code>MethodNotSupportedException</code>. Fires when default
     * method of service interface is not implemented.
     * @param mnse Exception that was caught.
     * @return Response message with <code>ResultCode.EXCEPTION</code> result code
     * and exception text message.
     */
    @ExceptionHandler(MethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<?> handleMethodNotSupportedException(MethodNotSupportedException mnse) {
        return new ResponseMessage<>(
                ResultCode.EXCEPTION,
                mnse.getMessage()
        );
    }

    /**
     * Handles <code>UserAuthenticationException</code>.
     * @param uae Exception that was caught.
     * @return Response message with <code>ResultCode.UNAUTHORIZED</code> result
     * code and exception text message.
     */
    @ExceptionHandler(UserAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseMessage<?> handleUserAuthenticationException(UserAuthenticationException uae) {
        return new ResponseMessage<>(
                ResultCode.UNAUTHORIZED,
                uae.getMessage()
        );
    }

    /**
     * Handles all <code>RuntimeException</code>s.
     * @param re Run time exemption to handle.
     * @return Response message with INTERNAL_SERVER_ERROR status code.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<?> handleRuntimeException(RuntimeException re) {
        return new ResponseMessage<>(
                ResultCode.EXCEPTION,
                re.getMessage()
        );
    }

    /**
     * Handles all <code>Exception</code>s.
     * @param e Exception to handle.
     * @return Response message with INTERNAL_SERVER_ERROR status code.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<?> handleException(Exception e) {
        return new ResponseMessage<>(
                ResultCode.EXCEPTION,
                e.getMessage()
        );
    }

    /**
     * Handles <code>ActionConfigurationNotValidException</code> which throws on action configuration checks fail.
     * @param acnve Exception to handle.
     * @return Response message with BAD_REQUEST status code.
     */
    @ExceptionHandler(ActionConfigurationNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<?> handleActionConfigurationNotValidException(ActionConfigurationNotValidException acnve) {
        return new ResponseMessage<>(
                ResultCode.FAIL,
                acnve.getMessage()
        );
    }
}

