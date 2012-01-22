package com.fundynamic.getitdone.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fundynamic.getitdone.domain.forms.RegistrationForm;

@Component
public class RegisterFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		RegistrationForm form = (RegistrationForm) target;
		if (!form.isAgreedWithTerms()) {
			errors.rejectValue("agreedWithTerms", "error");
		}
		
	}

}
