package com.socgen.ems.department;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;

import com.socgen.ems.department.data.Department;
import com.socgen.ems.department.data.DepartmentPayload;

@Component
public class DepartmentPayloadMapper {

	public DepartmentPayload getDepartmentPayload(Department department) {

		ModelMapper modelMapper = createModelMapper();
		return modelMapper.map(department, DepartmentPayload.class);
	}

	private ModelMapper createModelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
		return modelMapper;
	}
	
}
