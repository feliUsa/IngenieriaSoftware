package com.holaveci.RegisterLogin.Service;

import org.springframework.stereotype.Service;

import com.holaveci.RegisterLogin.Dto.EmployeeDTO;

@Service
public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);

}
