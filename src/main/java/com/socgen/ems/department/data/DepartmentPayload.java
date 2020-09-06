package com.socgen.ems.department.data;

public class DepartmentPayload {
	
	private Integer id;
	private String name;
	
	DepartmentPayload(){
		
	}
	
	public DepartmentPayload(Integer id, String name) {
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
