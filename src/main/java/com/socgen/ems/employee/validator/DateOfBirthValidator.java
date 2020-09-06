package com.socgen.ems.employee.validator;

import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import static com.socgen.ems.constants.EMSConstants.INPUT_DATE_FORMAT;

@Component
public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthValid, String> {
	
	private final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(INPUT_DATE_FORMAT);

	@Override
	public boolean isValid(String dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
		
		if(dateOfBirth == null){
			constraintValidatorContext.buildConstraintViolationWithTemplate("Date of birth cannot be null").addConstraintViolation().disableDefaultConstraintViolation();
			return false;
		}
		
		boolean isValid = true;
		try {
			DATE_FORMATTER.parse(dateOfBirth);
		} catch(Exception e){
			isValid = false;
		}
		return isValid;
	}
}