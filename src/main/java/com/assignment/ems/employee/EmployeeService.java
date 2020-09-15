package com.assignment.ems.employee;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assignment.ems.common.ResourceNotFoundException;
import com.assignment.ems.employee.data.Employee;
import com.assignment.ems.employee.data.EmployeePayload;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeePayloadMapper employeePayloadMapper;
	
	public List<EmployeePayload> retrieveEmployees(){
		
		List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));		
		return employees.isEmpty() ? Collections.emptyList() : employees.stream().map(employeePayloadMapper::getEmployeePayload).collect(Collectors.toList());
	}

	public EmployeePayload retrieveEmployee(Long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Employee id %d does not exist", id)));
		return employeePayloadMapper.getEmployeePayload(employee);
	}

	public EmployeePayload addEmployee(EmployeePayload employeePayload){
		
		Employee employee = employeePayloadMapper.getEmployeeFromPayload(employeePayload);
		return employeePayloadMapper.getEmployeePayload(employeeRepository.save(employee));
	}
	
}
