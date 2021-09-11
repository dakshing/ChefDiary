package com.mydiaries.chefdiary.user.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueMailIdValidator.class)
public @interface UniqueMailId {
    String message() default "Provided E-mail must be unique.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
