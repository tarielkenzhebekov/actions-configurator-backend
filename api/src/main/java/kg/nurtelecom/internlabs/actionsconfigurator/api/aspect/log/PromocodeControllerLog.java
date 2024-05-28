package kg.nurtelecom.internlabs.actionsconfigurator.api.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The <code>PromocodeControllerLog</code> class provides logging capabilities for controller actions in the application.
 */
@Aspect
@Component
public class PromocodeControllerLog {

    private final Logger logger = LoggerFactory.getLogger(PromocodeControllerLog.class);

    @Before("execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.promocode.PromocodeController.*(..))")
    public void logBeforePromocodeMethod(JoinPoint joinPoint) {
        String promocodeBeforeMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Promocode method {} called with arguments: {}", promocodeBeforeMethod, args);
    }

    @AfterReturning(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.promocode.PromocodeController.*(..))",returning = "result")
    public void logAfterReturnPromocodeMethod(JoinPoint joinPoint) {
        String promocodeAfterReturnMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Promocode method {} completed with result: {}", promocodeAfterReturnMethod, args);
    }

    @AfterThrowing(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.promocode.PromocodeController.*(..))", throwing = "exception")
    public void logAfterThrowPromocodeMethod(JoinPoint joinPoint) {
        String promocodeAfterThrowMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Promocode method {} threw an exception: {}", promocodeAfterThrowMethod, args);
    }
}
