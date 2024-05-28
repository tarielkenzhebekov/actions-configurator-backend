package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        acceptedValues =
                Stream.of(constraintAnnotation
                        .enumClass()
                        .getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            return acceptedValues.contains(value.toString());
        }
        return false;
    }
}
