package com.holaveci.RegisterLogin.Service;

import org.springframework.stereotype.Service;
import com.holaveci.RegisterLogin.Dto.EmployeeDTO;
import com.holaveci.RegisterLogin.Dto.LoginDTO;
import com.holaveci.RegisterLogin.response.LoginResponse;



@Service
public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
}
