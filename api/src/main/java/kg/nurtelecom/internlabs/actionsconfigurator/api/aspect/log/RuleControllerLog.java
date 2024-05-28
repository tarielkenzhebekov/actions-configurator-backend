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
 * The <code>RuleControllerLog</code> class provides logging capabilities for controller actions in the application.
 */
@Aspect
@Component
public class RuleControllerLog {

    private final Logger logger = LoggerFactory.getLogger(RuleControllerLog.class);

    @Before("execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.rule.RuleController.*(..))")
    public void logBeforeRuleMethod(JoinPoint joinPoint) {
        String ruleBeforeMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Rule method {} called with argument: {}", ruleBeforeMethod, args);
    }

    @AfterReturning(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.rule.RuleController.*(..))", returning = "result")
    public void logAfterReturnRuleMethod(JoinPoint joinPoint) {
        String ruleAfterReturnMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Rule method {} completed with result: {}", ruleAfterReturnMethod, args);
    }

    @AfterThrowing(pointcut = "execution(* kg.nurtelecom.internlabs.actionsconfigurator.api.controller.rule.RuleController.*(..))", throwing = "exception")
    public void logAfterThrowRuleMethod(JoinPoint joinPoint) {
        String ruleAfterThrowMethod = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Rule method {} threw an exception: {}", ruleAfterThrowMethod, args);
    }
}
