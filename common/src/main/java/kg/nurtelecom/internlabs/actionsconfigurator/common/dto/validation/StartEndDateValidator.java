package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDateTime;

/**
 * Custom validator for start-end date validation. Gets properties of class
 * annotated with <code>StartEndDate</code>. After receiving those properties
 * validator checks if start date and end date are not null and if start date
 * goes before end date.
 */
public class StartEndDateValidator implements ConstraintValidator<StartEndDate, Object> {

    private String startDate;
    private String endDate;

    @Override
    public void initialize(StartEndDate startEndDate) {
        this.startDate = startEndDate.startDate();
        this.endDate = startEndDate.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime startDateValue = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(startDate);
        LocalDateTime endDateValue = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(endDate);

        if ((startDateValue != null) && (endDateValue != null)) {
            return startDateValue.isBefore(endDateValue);
        }
        return false;
    }
}
