package com.socgen.ems.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.ems.employee.data.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
