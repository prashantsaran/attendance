package com.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.Employee;
import com.attendance.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	public Employee addEmployee(Employee e) {
		return employeeRepository.save(e);
	}

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

}