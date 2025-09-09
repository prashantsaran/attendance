package com.attendance.entity;

import java.time.LocalDateTime;

public class AttendanceRecordDto {
    private String punchType;
    private LocalDateTime timestamp;

    

    public AttendanceRecordDto(String punchType, LocalDateTime timestamp) {
        this.punchType = punchType;
        this.timestamp = timestamp;
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