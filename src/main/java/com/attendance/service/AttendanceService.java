package com.attendance.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.AttendanceRecord;
import com.attendance.entity.AttendanceRecordDto;
import com.attendance.entity.AttendanceResponse;
import com.attendance.entity.Employee;
import com.attendance.exception.EmployeeNotFoundException;
import com.attendance.exception.RecordNotFoundException;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.EmployeeRepository;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public AttendanceRecord markAttendance(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));

//        if (!employee.isPresent()) {
//            System.out.println("Employee not found with ID: " + employeeId);
//            return null;
//        }
        Optional<AttendanceRecord> lastRecordOpt = attendanceRepository.findTopByEmployeeOrderByTimestampDesc(employee);
        String nextPunchType = "IN";
        if (lastRecordOpt.isPresent() && "IN".equals(lastRecordOpt.get().getPunchType())) {
            nextPunchType = "OUT";
        }
        AttendanceRecord record = new AttendanceRecord();
        
//        Optional<Employee> employee = employeeRepository.findById(employeeId);
        record.setEmployee(employee);
        record.setPunchType(nextPunchType);
        record.setTimestamp(LocalDateTime.now());
        return attendanceRepository.save(record);
    }

//    public List<AttendanceRecord> getAttendance(Long employeeId, LocalDateTime from, LocalDateTime to) {
//        return attendanceRepository.findByEmployeeIdAndTimestampBetween(employeeId, from, to);
//    }

    public AttendanceResponse getAttendanceSummaryInMinutes(Long employeeId, LocalDateTime from, LocalDateTime to) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        List<AttendanceRecord> records = attendanceRepository.findByEmployeeAndTimestampBetween(employee, from, to);
        if(records.size()==0) {
            throw new RecordNotFoundException();
        }

        Map<String, List<AttendanceRecordDto>> dailyRecords = new LinkedHashMap<>();
        Map<String, Long> dailySummaryInMinutes = new LinkedHashMap<>();
        long totalMinutes = 0;

        // Group records by date
        Map<LocalDate, List<AttendanceRecord>> groupedByDate = records.stream()
            .collect(Collectors.groupingBy(r -> r.getTimestamp().toLocalDate(), TreeMap::new, Collectors.toList()));

        for (Map.Entry<LocalDate, List<AttendanceRecord>> entry : groupedByDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<AttendanceRecord> dailyList = entry.getValue();

            // Map to DTO list
            List<AttendanceRecordDto> dailyDtos = dailyList.stream()
                .map(r -> new AttendanceRecordDto(r.getPunchType(), r.getTimestamp()))
                .collect(Collectors.toList());
            dailyRecords.put(date.toString(), dailyDtos);

            // Calculate daily minutes worked
            long dailyMinutes = 0;
            for (int i = 0; i < dailyList.size() - 1; i += 2) {
                LocalDateTime inTime = dailyList.get(i).getTimestamp();
                LocalDateTime outTime = dailyList.get(i + 1).getTimestamp();
                dailyMinutes += Duration.between(inTime, outTime).toMinutes();
            }
            dailySummaryInMinutes.put(date.toString(), dailyMinutes);
            totalMinutes += dailyMinutes;
        }

        // Prepare response with minutes as strings
        Map<String, String> dailySummaryStr = new LinkedHashMap<>();
        for (Map.Entry<String, Long> e : dailySummaryInMinutes.entrySet()) {
            dailySummaryStr.put(e.getKey(), e.getValue() + " minutes");
        }

        AttendanceResponse response = new AttendanceResponse();
        response.setDailyRecords(dailyRecords);
        response.setDailySummary(dailySummaryStr);
        response.setTotalSummary(totalMinutes + " minutes");

        return response;
    }


    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        if (minutes == 0) {
            return hours + " hours";
        } else {
            return String.format("%d hours %d minutes", hours, minutes);
        }
    }

	public List<AttendanceRecord> findAllRecords() {
		return attendanceRepository.findAll();
	}
	
	public AttendanceRecord getLatestPunch(Long employeeId) {
		attendanceRepository.findById(employeeId).orElseThrow(()-> new EmployeeNotFoundException(employeeId) );
		AttendanceRecord rec = attendanceRepository.findLatestPunch(employeeId);
		Optional<AttendanceRecord> record = Optional.ofNullable(rec);	
		if(record.isEmpty()) {
			throw new RecordNotFoundException();
		}
		
		return attendanceRepository.findLatestPunch(employeeId);
	}

}
