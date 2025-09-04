package com.attendance.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.attendance.service.AttendanceService;


@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/punch")
    public ResponseEntity<?> punch(@RequestBody Map<String, Long> payload) {
      

        Long employeeId = payload.get("employeeId");
        AttendanceRecord record = attendanceService.markAttendance(employeeId);
        return ResponseEntity.ok(record);
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
}