package com.socgen.ems.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.socgen.ems.constants.Gender;

@Component
public class GenderValidator implements ConstraintValidator<GenderValid, String> {
	
	@Override
	public boolean isValid(String genderAsString, ConstraintValidatorContext constraintValidatorContext) {
		
		if(genderAsString == null){
			constraintValidatorContext.buildConstraintViolationWithTemplate("Gender cannot be null").addConstraintViolation().disableDefaultConstraintViolation();
			return false;
		}
		
		for(Gender gender : Gender.values()){
			if(gender.name().equals(genderAsString)){
				return true;
			}
		}
		return false;
	}
}