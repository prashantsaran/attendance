package com.attendance.exception;

import com.attendance.entity.MyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<MyResponse<?>> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        MyResponse<?> response = new MyResponse<>(
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class) // fallback for unexpected errors
    public ResponseEntity<MyResponse<?>> handleGenericException(Exception ex) {
        MyResponse<?> response = new MyResponse<>(
                "Internal Server Error: " + ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<MyResponse<?>> handleNoRecordFoundwithinSelectedRange(RecordNotFoundException r){
    	MyResponse<?> response = new MyResponse<>(
    			r.getMessage(),null,HttpStatus.OK.value());
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    			
    }
}
