package com.holaveci.RegisterLogin.EmployeeController;

import com.holaveci.RegisterLogin.Dto.EmployeeDTO;
import com.holaveci.RegisterLogin.Dto.LoginDTO;
import com.holaveci.RegisterLogin.response.LoginResponse;
import com.holaveci.RegisterLogin.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/vi/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        String id = employeeService.addEmployee(employeeDTO);
        return id;
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}

