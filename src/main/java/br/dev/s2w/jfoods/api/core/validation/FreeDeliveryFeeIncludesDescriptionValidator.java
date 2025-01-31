package br.dev.s2w.jfoods.api.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class FreeDeliveryFeeIncludesDescriptionValidator
        implements ConstraintValidator<FreeDeliveryFeeIncludesDescription, Object> {

    private String fieldValue;

    private String fieldDescription;

    private String requiredDescription;

    @Override
    public void initialize(FreeDeliveryFeeIncludesDescription constraintAnnotation) {
        this.fieldValue = constraintAnnotation.fieldValue();
        this.fieldDescription = constraintAnnotation.fieldDescription();
        this.requiredDescription = constraintAnnotation.requiredDescription();
    }

    @Override
    public boolean isValid(Object objectValidation, ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), fieldValue)
                    .getReadMethod().invoke(objectValidation);

            String description = (String) BeanUtils.getPropertyDescriptor(objectValidation.getClass(), fieldDescription)
                    .getReadMethod().invoke(objectValidation);

            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
                valid = description.toLowerCase().contains(this.requiredDescription.toLowerCase());
            }

            return valid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }

}
