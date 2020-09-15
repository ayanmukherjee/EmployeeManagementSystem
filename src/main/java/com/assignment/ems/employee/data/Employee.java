package com.assignment.ems.employee.data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.assignment.ems.constants.Gender;
import com.assignment.ems.department.data.Department;

@Entity
public class Employee {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private Gender gender;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@ManyToOne
	private Department department;
	
	@SuppressWarnings("unused")
	private Employee(){
		
	}
	
	public Employee(String firstName, String lastName, Gender gender, Date dateOfBirth, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Department getDepartment() {
		return department;
	}
	
}
