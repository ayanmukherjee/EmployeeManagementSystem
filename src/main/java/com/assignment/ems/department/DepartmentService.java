package com.assignment.ems.department;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.assignment.ems.department.data.Department;
import com.assignment.ems.department.data.DepartmentPayload;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DepartmentPayloadMapper departmentPayloadMapper;

	public List<DepartmentPayload> getDepartments(){
		
		List<Department> departments = departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		return departments.isEmpty() ? Collections.emptyList() : departments.stream().map(departmentPayloadMapper::getDepartmentPayload).collect(Collectors.toList());
	}

}
