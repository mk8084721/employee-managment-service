package com.mkcompany.employee_managment_service.service;

import com.mkcompany.employee_managment_service.entity.Employee;
import com.mkcompany.employee_managment_service.exception.EmployeeDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private Map<String , Employee> employees;
    private final PasswordEncoder passwordEncoder;

    public Employee createEmployee(String firstName ,String lastName ,String password){

        String email = firstName.toLowerCase() + lastName.toLowerCase()+"@company.com";

        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        employees.put(email ,employee);
        return employee;
    }
    public List<Employee> readAllEmployees(){
        return employees.values().stream().toList();
    }

    public Employee readEmployeeByEmail(String employeeEmail){
        if (!employees.containsKey(employeeEmail)){
            throw new EmployeeDoesNotExistException();
        }
        return employees.get(employeeEmail);
    }

    public Employee updateEmployee(String email ,Employee employee){
        if(!employees.containsKey(email)){
            throw new EmployeeDoesNotExistException("Employee to update does not exist!");
        }

        Employee presisted;

        if(!email.equals(employee.getEmail())){
            employees.remove(email);
            employees.put(employee.getEmail(), employee);
            presisted = employees.get(employee.getEmail());
        }else{
            presisted = employees.replace(email, employee);
        }

        return presisted;
    }
    public void deleteEmployee(String email){
        if(!employees.containsKey(email)){
            throw new EmployeeDoesNotExistException("Employee to delete does not exist!");
        }
        employees.remove(email);
    }

    public void loadEmployeeData(){
        employees = new HashMap<>();

        employees.put("adminemployee@mkcompany.com",Employee.builder()
                        .firstName("admin")
                        .lastName("employee")
                        .email("adminemployee@mkcompany.com")
                        .password("pass")
                        .build()
        );

        employees.put("manageremployee@mkcompany.com",Employee.builder()
                        .firstName("manager")
                        .lastName("employee")
                        .email("manageremployee@mkcompany.com")
                        .password("pass")
                        .build()
        );

        employees.put("employee1@mkcompany.com",Employee.builder()
                        .firstName("employee")
                        .lastName("one")
                        .email("employee1@mkcompany.com")
                        .password("pass")
                        .build()
        );
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Employee employee = readEmployeeByEmail(username);
            return User.builder()
                    .username(employee.getEmail())
                    .password(passwordEncoder.encode(employee.getPassword()))
                    .build();

        }catch (EmployeeDoesNotExistException e){
            throw new UsernameNotFoundException("Employee Does not exist!");
        }
    }
}
