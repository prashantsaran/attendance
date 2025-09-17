package com.attendance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	
private String name;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@OneToMany(mappedBy = "employee")
@JsonManagedReference
private List<AttendanceRecord> attendanceRecord;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Employee(String name, Long id) {
	super();
	this.name = name;
	this.id = id;
}
public List<AttendanceRecord> getAttendanceRecord() {
	return attendanceRecord;
}

public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Employee [name=" + name + ", id=" + id + "]";
}

}

