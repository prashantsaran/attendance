package com.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	List<Employee> getAllEmployee();
	
}
