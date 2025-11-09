package com.mkcompany.employee_managment_service.dto;

import lombok.Builder;

@Builder
public record LoginRequest(String email , String password) {
}
