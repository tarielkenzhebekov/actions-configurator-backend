package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom start-end dates validation constraint annotation. This annotation is
 * applicable to classes. If annotated then custom validator <code>StartEndDateValidator</code>
 * will get properties startDate and endDate of class and check if start date
 * is before the end date and vise versa. If validation failed then
 * code>MethodArgumentNotValidException</code> will be thrown.
 */
@Constraint(validatedBy = StartEndDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartEndDate {
    String startDate() default "startDate";

    String endDate() default "endDate";

    String message() default "Start date must be before end date and vise versa.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        StartEndDate[] value();
    }
}
