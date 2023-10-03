package com.holaveci.RegisterLogin.Service.impl;

import com.holaveci.RegisterLogin.Entity.Employee;
import com.holaveci.RegisterLogin.Dto.EmployeeDTO;
import com.holaveci.RegisterLogin.Repo.EmployeeRepo;
import com.holaveci.RegisterLogin.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );

        employeeRepo.save(employee);
        return employee.getEmployeename();

    }
}
