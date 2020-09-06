package com.socgen.ems.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.ems.department.data.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
}
