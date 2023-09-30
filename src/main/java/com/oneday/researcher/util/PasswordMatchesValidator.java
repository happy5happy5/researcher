package com.oneday.researcher.util;

import com.oneday.researcher.model.RegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationDTO user = (RegistrationDTO) obj;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
