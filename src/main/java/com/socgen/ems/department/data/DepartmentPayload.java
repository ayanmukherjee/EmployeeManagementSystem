package com.socgen.ems.department.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Department for employee is mandatory. The list of departments can be fetched from /departments/")
public class DepartmentPayload {
	
	@ApiModelProperty(required = true, notes = "Department Id", example = "1")
	private Integer id;
	@ApiModelProperty(notes = "Department name", example = "Finance")
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
