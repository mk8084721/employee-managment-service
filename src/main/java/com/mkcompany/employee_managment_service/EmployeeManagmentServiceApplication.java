package com.mkcompany.employee_managment_service;

import com.mkcompany.employee_managment_service.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagmentServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(EmployeeManagmentServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(EmployeeService employeeService){
		return (args -> {
			employeeService.loadEmployeeData();
		});
	}

}
