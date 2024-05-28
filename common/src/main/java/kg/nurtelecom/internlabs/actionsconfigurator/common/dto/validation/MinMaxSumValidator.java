package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MinMaxSumValidator implements ConstraintValidator<MinMaxSum, Object> {

    private String maxSum;
    private String minSum;

    @Override
    public void initialize(MinMaxSum constraintAnnotation) {
        minSum = constraintAnnotation.minSum();
        maxSum = constraintAnnotation.maxSum();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Double minSumValue = (Double) new BeanWrapperImpl(value).getPropertyValue(minSum);
        Double maxSumValue = (Double) new BeanWrapperImpl(value).getPropertyValue(maxSum);

        if ((minSumValue != null) && (maxSumValue != null)) {
            return minSumValue < maxSumValue;
        }
        return false;
    }
}
