package com.step.controller;

import com.step.form.FormException;
import com.step.form.RegisterUserForm;
import com.step.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Виктор on 30.11.2015.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl _service;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("model", new RegisterUserForm());
        return "register";
    }
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("model") RegisterUserForm model, Model viewModel) {

        try {
            _service.register(model);
        } catch (FormException e) {
            viewModel.addAttribute("errors", e.getErrors());
            viewModel.addAttribute("model", model);
            return "register";
        }
        return "register_success";
    }

}
