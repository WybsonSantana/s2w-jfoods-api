package br.dev.s2w.jfoods.api.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@PositiveOrZero
public @interface DeliveryFee {

    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{deliveryFee.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
