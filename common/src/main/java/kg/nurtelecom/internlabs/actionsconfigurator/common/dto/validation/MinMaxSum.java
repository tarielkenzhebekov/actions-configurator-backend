package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinMaxSumValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinMaxSum {
    String minSum() default "minSum";
    String maxSum() default "maxSum";
    String message() default "Minimal sum must be less then max sum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
