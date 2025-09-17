package com.attendance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AttendanceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Context up");		
	}
}
