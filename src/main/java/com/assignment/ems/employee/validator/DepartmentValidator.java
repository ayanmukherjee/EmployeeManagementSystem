package com.assignment.ems.employee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.ems.department.DepartmentService;
import com.assignment.ems.department.data.DepartmentPayload;

@Component
public class DepartmentValidator implements ConstraintValidator<DepartmentValid, DepartmentPayload> {

	@Autowired
	private DepartmentService departmentService;

	@Override
	public boolean isValid(DepartmentPayload departmentPayload, ConstraintValidatorContext constraintValidatorContext) {
		
		if(departmentPayload == null){
			constraintValidatorContext.buildConstraintViolationWithTemplate("Department cannot be null").addConstraintViolation().disableDefaultConstraintViolation();
			return false;
		}
		return departmentService.getDepartments().stream().filter(d -> d.getId().equals(departmentPayload.getId())).count() > 0;
		
	}
}