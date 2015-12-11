package com.step.form;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by Виктор on 01.12.2015.
 */
public class RegisterUserFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUserForm model = (RegisterUserForm)target;
        boolean isValid = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                .matcher(model.getEmail()).find();
        if (!isValid) {
            errors.rejectValue("email", "email", "Email не валиден");
        }
        if (model.getPassword().length() < 3 || model.getPassword().length() > 20) {
            errors.rejectValue("password", "password.length", "Password length must be between 4 and 20");
        }


    }
}
