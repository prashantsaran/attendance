package com.attendance.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.catalina.connector.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.entity.AttendanceRecord;
import com.attendance.entity.AttendanceResponse;
import com.attendance.entity.Employee;
import com.attendance.entity.MyResponse;
import com.attendance.service.AttendanceService;
import com.attendance.service.EmployeeService;



@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	
	

    private final AttendanceService attendanceService;
    private final EmployeeService employeeService;

    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @PostMapping("/punch")
    public ResponseEntity<MyResponse<Employee>> punch(@RequestBody Map<String, Long> payload) {
    	MyResponse response = new MyResponse();

        Long employeeId = payload.get("employeeId");
        
        AttendanceRecord record = attendanceService.markAttendance(employeeId);
        response.setData(record);
        response.setMessage("Attendance Marked Successfully !");
        response.setStatus(HttpStatus.OK.value());
   	 return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getAttendance(
            @PathVariable Long employeeId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

      

        LocalDateTime fromDateTime = from.atStartOfDay();
        LocalDateTime toDateTime = to.atTime(LocalTime.MAX);

        AttendanceResponse response = attendanceService.getAttendanceSummaryInMinutes(employeeId, fromDateTime, toDateTime);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee e){
    	
    	return employeeService.addEmployee(e);
    }
    
    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
    	return  employeeService.getAllEmployee();
    	
    }
    
    @GetMapping("/getAllRecords")
    public List<AttendanceRecord> getAllRecord(){
    	return  attendanceService.findAllRecords();
    }
    
    @GetMapping("/lastPunch/{id}")
    public ResponseEntity<MyResponse<Employee>> findLastPunch(@PathVariable Long id){
    	MyResponse response = new MyResponse<Employee>();
    	AttendanceRecord atResponse=  attendanceService.getLatestPunch(id);
    	response.setData(atResponse);
    	response.setStatus(HttpStatus.OK.value());
    	response.setMessage("Latest Attendance of employee with id : "+ id + "Fetched Successfully");
    	
    	return ResponseEntity.ok(response);
    	}
    
    
   
}