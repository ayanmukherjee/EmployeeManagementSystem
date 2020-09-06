package com.socgen.ems.employee.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socgen.ems.department.data.DepartmentPayload;
import com.socgen.ems.employee.validator.DateOfBirthValid;
import com.socgen.ems.employee.validator.DepartmentValid;
import com.socgen.ems.employee.validator.GenderValid;

import io.swagger.annotations.ApiModelProperty;

public class EmployeePayload {
	
	private Long id;

	@ApiModelProperty(value = "First name of the employee is mandatory and should be between 1-25 characters", example = "George")
	@NotNull(message = "First name cannot be null")
	@Size(min = 1, max = 25, message = "First name should be between 1-25 characters")
	private String firstName;
	
	@ApiModelProperty(value = "Last name of the employee is mandatory and should be between 1-25 characters", example = "Michael")
	@NotNull(message = "Last name cannot be null")
	@Size(min = 1, max = 25, message = "Last name should be between 1-25 characters")
	private String lastName;
	
	@ApiModelProperty(value = "Gender of the employee is mandatory and should be MALE, FEMALE or OTHER", example = "MALE")
	@GenderValid
	private String gender;
	
	@ApiModelProperty(value = "Date of Birth of the employee is mandatory and should be in yyyy-MM-dd format", example = "2019-12-31")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateOfBirthValid
	private String dateOfBirth;
	
	@ApiModelProperty(value = "Department of the employee is mandatory. The list of departments can be fetched from /departments", example = "{ \"id\": \"1\", \"name\": \"Finance\"}")
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
