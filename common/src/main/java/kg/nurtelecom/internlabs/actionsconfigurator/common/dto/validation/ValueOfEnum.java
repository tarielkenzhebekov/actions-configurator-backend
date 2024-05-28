package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom enum pattern validation constraint annotation. This annotation is applicable to
 * fields and parameters. If element is annotated with this annotations then request will
 * be checked if its field or fields is corresponding to enum constants.
 */
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Value must match '{pattern}'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
