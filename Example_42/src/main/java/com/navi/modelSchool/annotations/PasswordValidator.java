package com.navi.modelSchool.annotations;

import com.navi.modelSchool.validations.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //Optional and is this annotation is documented etc.
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Please Choose Strong Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
