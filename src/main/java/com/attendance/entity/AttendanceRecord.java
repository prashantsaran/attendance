package com.attendance.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
	@JsonBackReference
    private Employee employeeId;
    private String punchType; // "IN" or "OUT"
    private LocalDateTime timestamp;
    
    
    
    
	@Override
	public String toString() {
		return "AttendanceRecord [id=" + id + ", employeeId=" + employeeId + ", punchType=" + punchType + ", timestamp="
				+ timestamp + "]";
	}
	
	
	
	public AttendanceRecord() {
		super();
	}



	public AttendanceRecord(Long id, Employee employeeId, String punchType, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.punchType = punchType;
		this.timestamp = timestamp;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
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
