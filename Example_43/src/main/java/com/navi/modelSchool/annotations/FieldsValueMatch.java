package com.navi.modelSchool.annotations;


import com.navi.modelSchool.validations.FieldsValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class) //class --> implements this interface and here logic resides.
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();  // First of two fields to be matched.

    String fieldMatch(); // 2nd of two fields to be matched.

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }

}