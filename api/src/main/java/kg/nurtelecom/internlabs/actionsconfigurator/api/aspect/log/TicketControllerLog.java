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
 * The <code>TicketControllerLog</code> class provides logging capabilities for controller actions in the application.
 */
@Aspect
@Component
public class TicketControllerLog {

    private final Logger logger = LoggerFactory.getLogger(TicketControllerLog.class);

    @Before("execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.ticket.TicketController.*(..))")
    public void logBeforeTicketMethod(JoinPoint joinPoint) {
        String ticketBeforeMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Ticket method {} called with argument: {}", ticketBeforeMethod, args);
    }

    @AfterReturning(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.ticket.TicketController.*(..))",returning = "result")
    public void logAfterReturnTicketMethod(JoinPoint joinPoint) {
        String ticketAfterReturnMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Ticket method {} completed with result: {}", ticketAfterReturnMethod, args);
    }

    @AfterThrowing(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.ticket.TicketController.*(..))", throwing = "exception")
    public void logAfterThrowTicketMethod(JoinPoint joinPoint) {
        String ticketAfterThrowMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Ticket method {} threw an exception: {}", ticketAfterThrowMethod, args);
    }
}
