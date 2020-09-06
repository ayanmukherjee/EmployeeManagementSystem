package com.socgen.ems.employee.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.socgen.ems.BaseTest;
import com.socgen.ems.department.DepartmentService;
import com.socgen.ems.department.data.DepartmentPayload;

public class DepartmentValidatorTest extends BaseTest {
	
	@MockBean
	private DepartmentService mockDepartmentService;
	
	@InjectMocks
	private DepartmentValidator departmentValidator;
	
	@Mock
	private ConstraintValidatorContext mockConstraintValidatorContext;
	
	@Mock
	private ConstraintViolationBuilder mockConstraintViolationBuilder;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
	public void isValidShouldReturnFalseWhenDepartmentIsNull(){
    	
		Mockito.when(mockConstraintValidatorContext.buildConstraintViolationWithTemplate("Department cannot be null")).thenReturn(mockConstraintViolationBuilder);
		Mockito.when(mockConstraintViolationBuilder.addConstraintViolation()).thenReturn(mockConstraintValidatorContext);
		Mockito.doNothing().when(mockConstraintValidatorContext).disableDefaultConstraintViolation();

		Assert.assertFalse(departmentValidator.isValid(null, mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnFalseWhenDepartmentDoesNotExist(){
    	
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));

		Mockito.when(mockDepartmentService.getDepartments()).thenReturn(mockDepartments);
    	
		Assert.assertFalse(departmentValidator.isValid(new DepartmentPayload(3, "Department3"), mockConstraintValidatorContext));
	}
    
    @Test
	public void isValidShouldReturnTrueWhenDepartmentExists(){
    	
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));

		Mockito.when(mockDepartmentService.getDepartments()).thenReturn(mockDepartments);
    	
		Assert.assertTrue(departmentValidator.isValid(new DepartmentPayload(2, "Department2"), mockConstraintValidatorContext));
	}

}
