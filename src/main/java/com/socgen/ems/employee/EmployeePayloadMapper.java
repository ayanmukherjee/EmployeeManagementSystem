package com.socgen.ems.employee;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;

import com.socgen.ems.employee.data.Employee;
import com.socgen.ems.employee.data.EmployeePayload;

@Component
public class EmployeePayloadMapper {

	public Employee getEmployeeFromPayload(EmployeePayload employeeRequest) {

		ModelMapper modelMapper = createModelMapper();

		return modelMapper.map(employeeRequest, Employee.class);
	}

	public EmployeePayload getEmployeePayload(Employee employee) {

		ModelMapper modelMapper = createModelMapper();

		return modelMapper.map(employee, EmployeePayload.class);
	}

	private ModelMapper createModelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
		return modelMapper;
	}
	
}
