package uz.pdp.appwarehouseg8.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {
    private String password;
    private String prePassword;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        password = constraintAnnotation.password();
        prePassword = constraintAnnotation.prePassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return password != null && password.equals(prePassword);
    }
}
