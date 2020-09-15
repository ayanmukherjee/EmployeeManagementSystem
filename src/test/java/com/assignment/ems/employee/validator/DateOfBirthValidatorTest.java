package com.assignment.ems.employee.validator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.assignment.ems.BaseTest;
import com.assignment.ems.employee.validator.DateOfBirthValidator;

public class DateOfBirthValidatorTest extends BaseTest {
	
	@Mock
	private ConstraintValidatorContext mockConstraintValidatorContext;
	
	@Mock
	private ConstraintViolationBuilder mockConstraintViolationBuilder;
	
    @TestConfiguration
    static class DateOfBirthValidatorTestContextConfiguration {
 
        @Bean
        public DateOfBirthValidator dateOfBirthValidator() {
            return new DateOfBirthValidator();
        }
    }
 
    @Autowired
    private DateOfBirthValidator dateOfBirthValidator;
	
    @Test
	public void isValidShouldReturnFalseWhenDateOfBirthIsNull(){
    	
		Mockito.when(mockConstraintValidatorContext.buildConstraintViolationWithTemplate("Date of birth cannot be null")).thenReturn(mockConstraintViolationBuilder);
		Mockito.when(mockConstraintViolationBuilder.addConstraintViolation()).thenReturn(mockConstraintValidatorContext);
		Mockito.doNothing().when(mockConstraintValidatorContext).disableDefaultConstraintViolation();
    	
		Assert.assertFalse(dateOfBirthValidator.isValid(null, mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnFalseWhenDateOfBirthIsInvalid(){
    	
		Assert.assertFalse(dateOfBirthValidator.isValid("11/aa/2000", mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnTrueWhenDateOfBirthIsValid(){
    	
		Assert.assertTrue(dateOfBirthValidator.isValid("2000-10-31", mockConstraintValidatorContext));
	}

}
