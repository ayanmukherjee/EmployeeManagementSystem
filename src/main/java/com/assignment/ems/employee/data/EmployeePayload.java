package com.assignment.ems.employee.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assignment.ems.department.data.DepartmentPayload;
import com.assignment.ems.employee.validator.DateOfBirthValid;
import com.assignment.ems.employee.validator.DepartmentValid;
import com.assignment.ems.employee.validator.GenderValid;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class EmployeePayload {
	
	private Long id;

	@ApiModelProperty(required = true, notes = "First name of the employee is mandatory and should be between 1-25 characters", example = "George")
	@NotNull(message = "First name cannot be null")
	@Size(min = 1, max = 25, message = "First name should be between 1-25 characters")
	private String firstName;
	
	@ApiModelProperty(required = true, notes = "Last name of the employee is mandatory and should be between 1-25 characters", example = "Michael")
	@NotNull(message = "Last name cannot be null")
	@Size(min = 1, max = 25, message = "Last name should be between 1-25 characters")
	private String lastName;
	
	@ApiModelProperty(required = true, notes = "Gender of the employee is mandatory and should be MALE, FEMALE or OTHER", allowableValues = "MALE, FEMALE, OTHER", example = "MALE")
	@GenderValid
	private String gender;
	
	@ApiModelProperty(required = true, notes = "Date of Birth of the employee is mandatory and should be in yyyy-MM-dd format", example = "2019-12-31")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateOfBirthValid
	private String dateOfBirth;
	
	@ApiModelProperty(required = true)
	@DepartmentValid
	private DepartmentPayload department;
	
	public EmployeePayload(){
		
	}

	public EmployeePayload(Long id, String firstName, String lastName, String gender, String dateOfBirth, DepartmentPayload department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public DepartmentPayload getDepartment() {
		return department;
	}

}
