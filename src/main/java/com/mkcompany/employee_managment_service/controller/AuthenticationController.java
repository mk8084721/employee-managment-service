package com.mkcompany.employee_managment_service.controller;

import com.mkcompany.employee_managment_service.dto.LoginRequest;
import com.mkcompany.employee_managment_service.entity.Employee;
import com.mkcompany.employee_managment_service.exception.InvalidCredentialsException;
import com.mkcompany.employee_managment_service.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public Employee loginEmployee(@RequestBody LoginRequest body, HttpServletRequest request , HttpServletResponse response){
        return authenticationService.loginEmployee(body.email(), body.password(), request , response);
    }
    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<String> handelInvalidCredentialException(){
        return ResponseEntity.status(403).body("invalid email or password");
    }
}
