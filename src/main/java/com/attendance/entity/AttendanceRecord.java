package com.attendance.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
	@JsonBackReference
	@JoinColumn(name = "employee_id")
    private Employee employee;
    private String punchType; // "IN" or "OUT"
    private LocalDateTime timestamp;
    
    
    
    
	@Override
	public String toString() {
		return "AttendanceRecord [id=" + id + ", employeeId=" + employee + ", punchType=" + punchType + ", timestamp="
				+ timestamp + "]";
	}
	
	
	
	public AttendanceRecord() {
		super();
	}



	public AttendanceRecord(Long id, Employee employee, String punchType, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.employee = employee;
		this.punchType = punchType;
		this.timestamp = timestamp;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employeeId) {
		this.employee = employeeId;
	}
	public String getPunchType() {
		return punchType;
	}
	public void setPunchType(String punchType) {
		this.punchType = punchType;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
