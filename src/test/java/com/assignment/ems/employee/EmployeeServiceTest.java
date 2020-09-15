package com.assignment.ems.employee;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.assignment.ems.BaseTest;
import com.assignment.ems.common.ResourceNotFoundException;
import com.assignment.ems.constants.Gender;
import com.assignment.ems.department.data.Department;
import com.assignment.ems.department.data.DepartmentPayload;
import com.assignment.ems.employee.EmployeePayloadMapper;
import com.assignment.ems.employee.EmployeeRepository;
import com.assignment.ems.employee.EmployeeService;
import com.assignment.ems.employee.data.Employee;
import com.assignment.ems.employee.data.EmployeePayload;

public class EmployeeServiceTest extends BaseTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@MockBean
	private EmployeeRepository mockEmployeeRepository;
	
	@MockBean
	private EmployeePayloadMapper mockEmployeePayloadMapper;
	
	@InjectMocks
	private EmployeeService employeeService;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void retrieveEmployeesWhenNoneExists(){
    	
		List<Employee> mockEmployees =  Collections.emptyList();
		
		List<EmployeePayload> mockEmployeePayloads = Collections.emptyList();;		
		
		Mockito.when(mockEmployeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenReturn(mockEmployees);
    	
		Assert.assertEquals(employeeService.retrieveEmployees(), mockEmployeePayloads);
	}
    
    @Test
	public void retrieveEmployees(){
    	
		Department mockDepartment = new Department(1 , "Department1");
		
		List<Employee> mockEmployees = Arrays.asList(
				new Employee("George", "Michael", Gender.MALE, new Date(System.currentTimeMillis()), mockDepartment),
				new Employee("Sheryl", "Crow", Gender.FEMALE, new Date(System.currentTimeMillis()), mockDepartment)
				);
		
		DepartmentPayload mockDepartmentPayload = new DepartmentPayload(1 , "Department1");
		
		List<EmployeePayload> mockEmployeePayloads = Arrays.asList(
				new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "11/02/1967", mockDepartmentPayload),
				new EmployeePayload(2l, "Sheryl", "Crow", Gender.FEMALE.toString(), "26/05/1970", mockDepartmentPayload)
				);		
		
		Mockito.when(mockEmployeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenReturn(mockEmployees);
		Mockito.when(mockEmployeePayloadMapper.getEmployeePayload(mockEmployees.get(0))).thenReturn(mockEmployeePayloads.get(0));
		Mockito.when(mockEmployeePayloadMapper.getEmployeePayload(mockEmployees.get(1))).thenReturn(mockEmployeePayloads.get(1));
    	
		Assert.assertEquals(employeeService.retrieveEmployees(), mockEmployeePayloads);
	}

    @Test
	public void retrieveEmployeeThatDoesNotExist(){

    	Optional<Employee> mockOptionalEmployee = Optional.empty();
		
		Mockito.when(mockEmployeeRepository.findById(1l)).thenReturn(mockOptionalEmployee);

		expectedException.expect(ResourceNotFoundException.class);
		expectedException.expectMessage("Employee id 1 does not exist");
		
		employeeService.retrieveEmployee(1l);		
	}
    
    @Test
	public void retrieveEmployee(){
    	
		Department mockDepartment = new Department(1 , "Department1");
		
		Optional<Employee> mockOptionalEmployee = Optional.of(new Employee("George", "Michael", Gender.MALE, new Date(System.currentTimeMillis()), mockDepartment));
		
		Employee mockEmployee = mockOptionalEmployee.get();
		
		DepartmentPayload mockDepartmentPayload = new DepartmentPayload(1 , "Department1");
		
		EmployeePayload mockEmployeePayload = new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "11/02/1967", mockDepartmentPayload);		
		
		Mockito.when(mockEmployeeRepository.findById(1l)).thenReturn(mockOptionalEmployee);
		Mockito.when(mockEmployeePayloadMapper.getEmployeePayload(mockEmployee)).thenReturn(mockEmployeePayload);
    	
		Assert.assertEquals(employeeService.retrieveEmployee(1l), mockEmployeePayload);
	}
    
    @Test
	public void addEmployee(){
    	
		Department mockDepartment = new Department(1 , "Department1");		
		Employee mockEmployee = new Employee("George", "Michael", Gender.MALE, new Date(System.currentTimeMillis()), mockDepartment);
		
		DepartmentPayload mockDepartmentPayload = new DepartmentPayload(1 , "Department1");		
		EmployeePayload mockEmployeePayload = new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "11/02/1967", mockDepartmentPayload);		
		
		Mockito.when(mockEmployeePayloadMapper.getEmployeeFromPayload(mockEmployeePayload)).thenReturn(mockEmployee);
		Mockito.when(mockEmployeeRepository.save(mockEmployee)).thenReturn(mockEmployee);
		Mockito.when(mockEmployeePayloadMapper.getEmployeePayload(mockEmployee)).thenReturn(mockEmployeePayload);
    	
		Assert.assertEquals(employeeService.addEmployee(mockEmployeePayload), mockEmployeePayload);
	}
    


}
