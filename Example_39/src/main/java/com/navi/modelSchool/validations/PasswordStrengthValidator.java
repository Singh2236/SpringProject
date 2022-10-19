package com.navi.modelSchool.validations;

import com.navi.modelSchool.annotations.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;


    @Override
    public void initialize(PasswordValidator passwordValidator) { // data/business logic needed to validations
        weakPasswords = Arrays.asList("12345", "password", "qwerty"); //list to check weak passwords
    }

    @Override //if isValid fails, requirement of this particular business logic is not fulfilled.
    public boolean isValid(String passwordField, ConstraintValidatorContext context) { //1.para = value entered by user, 2.param context from initialize method
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}
