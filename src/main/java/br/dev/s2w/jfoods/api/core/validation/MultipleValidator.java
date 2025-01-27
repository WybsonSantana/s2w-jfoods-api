package br.dev.s2w.jfoods.api.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int multipleNumber;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.multipleNumber = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (value != null) {
            BigDecimal decimalValue = BigDecimal.valueOf(value.doubleValue());
            BigDecimal decimalMultiple = BigDecimal.valueOf(this.multipleNumber);
            BigDecimal rest = decimalValue.remainder(decimalMultiple);

            valid = BigDecimal.ZERO.compareTo(rest) == 0;
        }

        return valid;
    }

}
