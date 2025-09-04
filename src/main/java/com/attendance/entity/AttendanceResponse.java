package com.attendance.entity;

import java.util.List;
import java.util.Map;

public class AttendanceResponse {
    public AttendanceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Map<String, List<AttendanceRecordDto>> dailyRecords;
    private Map<String, String> dailySummary;
    private String totalSummary;
    
    
	public AttendanceResponse(Map<String, List<AttendanceRecordDto>> dailyRecords, Map<String, String> dailySummary,
			String totalSummary) {
		super();
		this.dailyRecords = dailyRecords;
		this.dailySummary = dailySummary;
		this.totalSummary = totalSummary;
	}
	public Map<String, List<AttendanceRecordDto>> getDailyRecords() {
		return dailyRecords;
	}
	public void setDailyRecords(Map<String, List<AttendanceRecordDto>> dailyRecords) {
		this.dailyRecords = dailyRecords;
	}
	public Map<String, String> getDailySummary() {
		return dailySummary;
	}
	public void setDailySummary(Map<String, String> dailySummary) {
		this.dailySummary = dailySummary;
	}
	public String getTotalSummary() {
		return totalSummary;
	}
	public void setTotalSummary(String totalSummary) {
		this.totalSummary = totalSummary;
	}

    // constructors, getters, setters
}