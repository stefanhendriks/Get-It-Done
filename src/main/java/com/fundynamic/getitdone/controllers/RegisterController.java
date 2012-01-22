package com.fundynamic.getitdone.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fundynamic.getitdone.domain.forms.RegistrationForm;
import com.fundynamic.getitdone.validators.RegisterFormValidator;

@Controller
public class RegisterController {

    protected final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterFormValidator formValidator;
    
    @RequestMapping(value = "/registration.htm")
    public String handleRequest(ModelMap modelMap, RegistrationForm registrationForm, Errors errors) {
    	logger.debug("registration form, using object: {}", registrationForm);
    	formValidator.validate(registrationForm, errors);
    	modelMap.addAttribute("registrationForm", registrationForm);
        return "registerForm";
    }

}