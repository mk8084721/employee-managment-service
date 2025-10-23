package com.mkcompany.employee_managment_service.dto;

import com.mkcompany.employee_managment_service.entity.Employee;
import lombok.Builder;

@Builder
public record UpdateEmployeeRequest(String email , Employee employee) {
}
