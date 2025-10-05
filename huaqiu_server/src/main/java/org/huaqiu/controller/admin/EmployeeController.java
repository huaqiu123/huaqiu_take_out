package org.huaqiu.controller.admin;

import org.apache.ibatis.annotations.ResultType;
import org.huaqiu.dto.EmployeeLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.huaqiu.service.EmployeeService;
@RestController
@RequestMapping("admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeLoginDTO employeeLoginDTO;
//
//    private JwtProperties jwtProperties;
    @GetMapping
    public void  HelloWorld(){
        System.out.println("HelloWorld");
        employeeService.login(employeeLoginDTO);

    }

}
