package com.assignment.ems.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.ems.department.data.DepartmentPayload;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/")
	public ResponseEntity<List<DepartmentPayload>> getDepartments() {

		return ResponseEntity.ok(departmentService.getDepartments());
	}

}
