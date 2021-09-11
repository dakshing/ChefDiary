package com.mydiaries.chefdiary.user.validators;

import com.mydiaries.chefdiary.user.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMailIdValidator implements ConstraintValidator<UniqueMailId, String> {

    private final UserService userService;

    public UniqueMailIdValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String mailId, ConstraintValidatorContext constraintValidatorContext) {
        return userService.isEmailUnique(mailId);
    }

    @Override
    public void initialize(UniqueMailId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
