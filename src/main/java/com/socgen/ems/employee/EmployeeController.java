package com.socgen.ems.employee;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socgen.ems.employee.data.EmployeePayload;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeePayload>> getEmployees() {

		return ResponseEntity.ok(employeeService.retrieveEmployees());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeePayload> getEmployee(@PathVariable Long id) {

		return ResponseEntity.ok(employeeService.retrieveEmployee(id));
	}

	@PostMapping("/")
	public ResponseEntity<Void> addEmployee(@Valid @RequestBody EmployeePayload employeePayload){
		
		EmployeePayload result = employeeService.addEmployee(employeePayload);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
