package com.mkcompany.employee_managment_service.exception;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
