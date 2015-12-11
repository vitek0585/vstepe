package com.step.form;

/**
 * Created by Виктор on 01.12.2015.
 */
public class RegisterUserForm {

    private String email;
    private String password;


    public RegisterUserForm() {
    }

    public RegisterUserForm(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
