package com.attendance.controller;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
@Component
public class LoggingAspect {

  
	@Before("execution(org.springframework.http.ResponseEntity *..punch*(..))")
	public void trackMethod() {
	    System.out.println("trackMethod() was executed");
	}
	
	
	@Before("execution(* com.attendance.controller.*.*(..))")
	public void executeBeforeinController(JoinPoint jointpoint) {
	    System.out.println("Before Aspect Method was called -{}"+jointpoint);
	}


}