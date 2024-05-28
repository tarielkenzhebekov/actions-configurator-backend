package kg.nurtelecom.internlabs.actionsconfigurator.api.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The <code>ActionControllerLog</code> class provides logging capabilities for controller actions in the application.
 */
@Aspect
@Component
public class ActionControllerLog {

    private final Logger logger = LoggerFactory.getLogger(ActionControllerLog.class);

    @Before("execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action.ActionController.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        String controllerBeforeMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Controller method {} called with arguments: {}", controllerBeforeMethod, args);
    }

    @AfterReturning(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action.ActionController.*(..))", returning = "result")
    public void logAfterControllerMethod(JoinPoint joinPoint, Object result) {
        String controllerAfterReturnMethod = joinPoint.getSignature().getName();
        logger.info("Controller method {} completed with result: {}", controllerAfterReturnMethod, result);
    }

    @AfterThrowing(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action.ActionController.*(..))", throwing = "exception")
    public void logAfterThrowingControllerMethod(JoinPoint joinPoint, Throwable exception) {
        String controllerAfterThrowMethod = joinPoint.getSignature().getName();
        logger.error("Controller method {} threw an exception: {}", controllerAfterThrowMethod, exception.getMessage());
    }
}
