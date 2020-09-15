package com.assignment.ems.employee.validator;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.assignment.ems.BaseTest;
import com.assignment.ems.employee.validator.GenderValidator;

public class GenderValidatorTest extends BaseTest {
	
	@Mock
	private ConstraintValidatorContext mockConstraintValidatorContext;
	
	@Mock
	private ConstraintViolationBuilder mockConstraintViolationBuilder;
	
    @Test
	public void isValidShouldReturnFalseWhenGenderIsNull(){
    	
		Mockito.when(mockConstraintValidatorContext.buildConstraintViolationWithTemplate("Gender cannot be null")).thenReturn(mockConstraintViolationBuilder);
		Mockito.when(mockConstraintViolationBuilder.addConstraintViolation()).thenReturn(mockConstraintValidatorContext);
		Mockito.doNothing().when(mockConstraintValidatorContext).disableDefaultConstraintViolation();
    	
		GenderValidator genderValidator = new GenderValidator();
		Assert.assertFalse(genderValidator.isValid(null, mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnFalseWhenGenderDoesNotExist(){
    	
    	GenderValidator genderValidator = new GenderValidator();
		Assert.assertFalse(genderValidator.isValid("NONE", mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnTrueWhenGenderExists(){
    	
    	GenderValidator genderValidator = new GenderValidator();
		Assert.assertTrue(genderValidator.isValid("MALE", mockConstraintValidatorContext));
	}

}
