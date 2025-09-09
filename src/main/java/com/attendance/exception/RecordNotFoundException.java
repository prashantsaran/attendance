package com.attendance.exception;

public class RecordNotFoundException extends RuntimeException{

	
	public RecordNotFoundException(){
        super("No Record found between selected range");
	}
}
