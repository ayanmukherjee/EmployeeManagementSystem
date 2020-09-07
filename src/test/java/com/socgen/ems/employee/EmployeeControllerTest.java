package com.socgen.ems.employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import com.socgen.ems.BaseControllerTest;
import com.socgen.ems.constants.Gender;
import com.socgen.ems.department.DepartmentService;
import com.socgen.ems.department.data.DepartmentPayload;
import com.socgen.ems.employee.data.EmployeePayload;

@WebMvcTest(value = EmployeeController.class)
@SuppressWarnings("unchecked")
public class EmployeeControllerTest extends BaseControllerTest {
	
	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private DepartmentService departmentService;
	
	@Test
	public void retrieveEmployees() throws Exception {
		
		DepartmentPayload mockDepartment = new DepartmentPayload(1 , "Department1");
		
		List<EmployeePayload> mockEmployees = Arrays.asList(
				new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "1967-02-11", mockDepartment),
				new EmployeePayload(2l, "Sheryl", "Crow", Gender.FEMALE.toString(), "1970-05-26", mockDepartment)
				);
				
		Mockito.when(employeeService.retrieveEmployees()).thenReturn(mockEmployees);

		String responseAsString = executeGetRequest("/employees/").getContentAsString();

		String expectedResponse = "[{firstName:George, lastName: Michael},{firstName:Sheryl, lastName: Crow}]";
		JSONAssert.assertEquals(expectedResponse, responseAsString, false);
	}
	
	@Test
	public void retrieveEmployee() throws Exception {
		
		DepartmentPayload mockDepartment = new DepartmentPayload(1 , "Department1");
		
		EmployeePayload mockEmployee = new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "1967-02-11", mockDepartment);
		
		Mockito.when(employeeService.retrieveEmployee(1l)).thenReturn(mockEmployee);

		String responseAsString = executeGetRequest("/employees/1").getContentAsString();

		String expectedResponse = "{firstName:George, lastName: Michael}";
		JSONAssert.assertEquals(expectedResponse, responseAsString, false);
	}
	
	@Test
	public void addEmployee() throws Exception {
		
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));

		Mockito.when(departmentService.getDepartments()).thenReturn(mockDepartments);
		
		DepartmentPayload mockDepartment = new DepartmentPayload(1 , "Department1");
		
		EmployeePayload mockEmployee = new EmployeePayload(1l, "George", "Michael", Gender.MALE.toString(), "1967-02-11", mockDepartment);
				
		Mockito.when(employeeService.addEmployee(Mockito.any(EmployeePayload.class))).thenReturn(mockEmployee);

		MockHttpServletResponse response = executePostRequest("/employees/", mapToJSON(mockEmployee));
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		Assert.assertEquals("http://localhost/employees/1", response.getHeader(HttpHeaders.LOCATION));		
	}
	
	@Test
	public void addEmployeeWithNoAttributes() throws Exception {
		
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));

		Mockito.when(departmentService.getDepartments()).thenReturn(mockDepartments);
		
		EmployeePayload mockEmployee = new EmployeePayload();
		
		Mockito.when(employeeService.addEmployee(Mockito.any(EmployeePayload.class))).thenReturn(mockEmployee);

		MockHttpServletResponse response = executePostRequest("/employees/", mapToJSON(mockEmployee));
		
		Map<String, String> errors = mapFromJSON(response.getContentAsString(), Map.class);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		Assert.assertEquals(errors.get("firstName"), "First name cannot be null");
		Assert.assertEquals(errors.get("lastName"), "Last name cannot be null");
		Assert.assertEquals(errors.get("gender"), "Gender cannot be null");
		Assert.assertEquals(errors.get("dateOfBirth"), "Date of birth cannot be null");		
		Assert.assertEquals(errors.get("department"), "Department cannot be null");
	}
	
	@Test
	public void addEmployeeWithInvalidAttributes() throws Exception {
		
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));
		
		DepartmentPayload mockDepartment = new DepartmentPayload(3 , "Department3");

		String textWithTwentySixCharacters = "abcdefghijklmnopqrstuvwxyz";
		
		String invalidDate = "abcd";
		
		EmployeePayload mockEmployee = new EmployeePayload(null, textWithTwentySixCharacters, textWithTwentySixCharacters, "NONE", invalidDate, mockDepartment);
		
		Mockito.when(departmentService.getDepartments()).thenReturn(mockDepartments);
		
		Mockito.when(employeeService.addEmployee(Mockito.any(EmployeePayload.class))).thenReturn(mockEmployee);

		MockHttpServletResponse response = executePostRequest("/employees/", mapToJSON(mockEmployee));
		
		Map<String, String> errors = mapFromJSON(response.getContentAsString(), Map.class);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		Assert.assertEquals(errors.get("firstName"), "First name should be between 1-25 characters");
		Assert.assertEquals(errors.get("lastName"), "Last name should be between 1-25 characters");
		Assert.assertEquals(errors.get("gender"), "Gender is invalid");
		Assert.assertEquals(errors.get("dateOfBirth"), "Date of birth is invalid");		
		Assert.assertEquals(errors.get("department"), "Department is invalid");
	}
	
	@Test
	public void addEmployeeWithEmptyAttributes() throws Exception {
		
		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));
		
		DepartmentPayload mockDepartment = new DepartmentPayload(3 , "Department3");

		EmployeePayload mockEmployee = new EmployeePayload(null, "", "", "", "", mockDepartment);
		
		Mockito.when(departmentService.getDepartments()).thenReturn(mockDepartments);
		
		Mockito.when(employeeService.addEmployee(Mockito.any(EmployeePayload.class))).thenReturn(mockEmployee);

		MockHttpServletResponse response = executePostRequest("/employees/", mapToJSON(mockEmployee));
		
		Map<String, String> errors = mapFromJSON(response.getContentAsString(), Map.class);
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		Assert.assertEquals(errors.get("firstName"), "First name should be between 1-25 characters");
		Assert.assertEquals(errors.get("lastName"), "Last name should be between 1-25 characters");
		Assert.assertEquals(errors.get("gender"), "Gender is invalid");
		Assert.assertEquals(errors.get("dateOfBirth"), "Date of birth is invalid");		
		Assert.assertEquals(errors.get("department"), "Department is invalid");
	}


}
