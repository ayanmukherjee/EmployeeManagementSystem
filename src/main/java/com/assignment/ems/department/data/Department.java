package com.assignment.ems.department.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department implements Serializable {
	
	private static final long serialVersionUID = 7965803529362639606L;
	
	@Id
	private Integer id;
	private String name;
	
	@SuppressWarnings("unused")
	private Department(){
		
	}

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
