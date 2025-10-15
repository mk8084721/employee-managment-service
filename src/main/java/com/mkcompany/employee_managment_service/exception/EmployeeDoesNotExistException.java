package com.mkcompany.employee_managment_service.exception;

public class EmployeeDoesNotExistException extends RuntimeException{
    public EmployeeDoesNotExistException(){
        super("Employee Does Not Exist!");
    }

    public EmployeeDoesNotExistException (String message){
        super(message);
    }
}
