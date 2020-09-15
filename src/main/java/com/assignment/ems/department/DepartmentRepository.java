package com.assignment.ems.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.ems.department.data.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
}
