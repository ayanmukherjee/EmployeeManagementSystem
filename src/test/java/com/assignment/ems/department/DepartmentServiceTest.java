package com.assignment.ems.department;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.assignment.ems.BaseTest;
import com.assignment.ems.department.DepartmentPayloadMapper;
import com.assignment.ems.department.DepartmentRepository;
import com.assignment.ems.department.DepartmentService;
import com.assignment.ems.department.data.Department;
import com.assignment.ems.department.data.DepartmentPayload;

public class DepartmentServiceTest extends BaseTest {

	@MockBean
	private DepartmentRepository mockDepartmentRepository;
	
	@MockBean
	private DepartmentPayloadMapper mockDepartmentPayloadMapper;
	
	@InjectMocks
	private DepartmentService departmentService;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void retrieveDepartmentsWhenNoneExists(){
    	
		List<Department> mockDepartments =  Collections.emptyList();
		
		List<DepartmentPayload> mockDepartmentPayloads = Collections.emptyList();	
		
		Mockito.when(mockDepartmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(mockDepartments);
    	
		Assert.assertEquals(departmentService.getDepartments(), mockDepartmentPayloads);
	}
    
    @Test
	public void retrieveDepartments(){
    	
    	List<Department> mockDepartments = Arrays.asList(new Department(1, "Department1"), new Department(2, "Department2"));
    	List<DepartmentPayload> mockDepartmentPayloads = Arrays.asList(new DepartmentPayload(1, "Department1"), new DepartmentPayload(2, "Department2"));
		
		Mockito.when(mockDepartmentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(mockDepartments);

		Mockito.when(mockDepartmentPayloadMapper.getDepartmentPayload(mockDepartments.get(0))).thenReturn(mockDepartmentPayloads.get(0));
		Mockito.when(mockDepartmentPayloadMapper.getDepartmentPayload(mockDepartments.get(1))).thenReturn(mockDepartmentPayloads.get(1));
    	
		Assert.assertEquals(departmentService.getDepartments(), mockDepartmentPayloads);
	}
}
