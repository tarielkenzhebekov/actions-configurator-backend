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
 * The <code>StageControllerLog</code> class provides logging capabilities for controller actions in the application.
 */
@Aspect
@Component
public class StageControllerLog {

    private final Logger logger = LoggerFactory.getLogger(StageControllerLog.class);

    @Before("execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.stage.StageController.*(..))")
    public void logBeforeStageMethod(JoinPoint joinPoint) {
        String stageBeforeMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Stage method {} called with argument: {}", stageBeforeMethod, args);
    }

    @AfterReturning(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.stage.StageController.*(..))",returning = "result")
    public void logAfterReturnStageMethod(JoinPoint joinPoint) {
        String stageAfterReturnMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Stage method {} completed with result: {}", stageAfterReturnMethod, args);
    }

    @AfterThrowing(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.stage.StageController.*(..))", throwing = "exception")
    public void logAfterThrowStageMethod(JoinPoint joinPoint) {
        String stageAfterThrowMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Stage method {} threw an exception: {}", stageAfterThrowMethod, args);
    }
}
