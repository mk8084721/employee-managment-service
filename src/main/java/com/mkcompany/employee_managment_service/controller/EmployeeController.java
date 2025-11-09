package com.mkcompany.employee_managment_service.controller;

import com.mkcompany.employee_managment_service.dto.CreateEmployeeRequest;
import com.mkcompany.employee_managment_service.dto.UpdateEmployeeRequest;
import com.mkcompany.employee_managment_service.entity.Employee;
import com.mkcompany.employee_managment_service.exception.EmployeeDoesNotExistException;
import com.mkcompany.employee_managment_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService  employeeService;

    @PostMapping("/")
    public Employee postEmployee(@RequestBody CreateEmployeeRequest body){
        return employeeService.createEmployee(body.firstName(), body.lastName(), body.password());
    }
    @GetMapping("/")
    public List<Employee> getAllEmployees(){
        return employeeService.readAllEmployees();
    }
    @GetMapping("/email/{email}")
    public Employee getEmployeeByEmail(@PathVariable("email") String email){
        return employeeService.readEmployeeByEmail(email);
    }
    @PutMapping("/")
    public Employee updateEmployeeByEmail(@RequestBody UpdateEmployeeRequest body){
        return employeeService.updateEmployee(body.email(), body.employee());
    }
    @DeleteMapping("/{email}")
    public ResponseEntity deleteEmployee(@PathVariable("email") String email){
        employeeService.deleteEmployee(email);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({EmployeeDoesNotExistException.class})
    public ResponseEntity<String> handleEmployeeDoesNotExist(){
        return ResponseEntity.status(400).body("unable to handle request at this time!");
    }

}
