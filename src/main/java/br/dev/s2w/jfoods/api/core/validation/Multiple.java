package br.dev.s2w.jfoods.api.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {MultipleValidator.class})
public @interface Multiple {

    String message() default "Multiple invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int number();

}
