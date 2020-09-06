package com.socgen.ems.department;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.socgen.ems.BaseControllerTest;
import com.socgen.ems.department.data.DepartmentPayload;

@WebMvcTest(value = DepartmentController.class)
public class DepartmentControllerTest extends BaseControllerTest {

	@MockBean
	private DepartmentService departmentService;

	@Test
	public void retrieveDepartments() throws Exception {

		List<DepartmentPayload> mockDepartments = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));

		Mockito.when(departmentService.getDepartments()).thenReturn(mockDepartments);

		String responseAsString = executeGetRequest("/departments/").getContentAsString();

		String expectedResponse = "[{id:1,name:Department1},{id:2,name:Department2}]";
		JSONAssert.assertEquals(expectedResponse, responseAsString, true);
	}

}
