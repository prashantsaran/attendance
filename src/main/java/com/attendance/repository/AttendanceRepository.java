package com.attendance.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entity.AttendanceRecord;
import com.attendance.entity.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByEmployeeIdAndTimestampBetween(Employee employeeId, LocalDateTime from, LocalDateTime to);
    Optional<AttendanceRecord> findTopByEmployeeIdOrderByTimestampDesc(Employee employeeId);
}
