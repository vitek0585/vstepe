package com.step.form;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by Виктор on 01.12.2015.
 */
public class FormException extends Exception {
    private List<ObjectError> errors;

    public FormException(List<ObjectError> errors) {
        super();
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
