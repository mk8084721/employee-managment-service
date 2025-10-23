package com.mkcompany.employee_managment_service.dto;

import lombok.Builder;

@Builder
public record CreateEmployeeRequest(String firstName, String lastName ,String password){
}
