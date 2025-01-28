package br.dev.s2w.jfoods.api.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FreeDeliveryFeeIncludesDescriptionValidator.class})
public @interface FreeDeliveryFeeIncludesDescription {

    String message() default "Invalid required description";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldValue();

    String fieldDescription();

    String requiredDescription();

}
