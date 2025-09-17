package com.attendance.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attendance.entity.AttendanceRecord;
import com.attendance.entity.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
//    List<AttendanceRecord> findByEmployeeIdAndTimestampBetween(Employee employee, LocalDateTime from, LocalDateTime to);
//    Optional<AttendanceRecord> findTopByEmployeeIdOrderByTimestampDesc(Employee employee);
    
    List<AttendanceRecord> findByEmployeeAndTimestampBetween(Employee employee, LocalDateTime from, LocalDateTime to);

    Optional<AttendanceRecord> findTopByEmployeeOrderByTimestampDesc(Employee employee);

    
    
    @Query("SELECT a FROM AttendanceRecord a " +
            "WHERE a.employee.id = :empId " +
            "ORDER BY a.timestamp DESC LIMIT 1")
     AttendanceRecord findLatestPunch(@Param("empId") Long employeeId);
}