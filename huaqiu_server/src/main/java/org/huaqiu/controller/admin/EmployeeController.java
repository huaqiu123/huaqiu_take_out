package org.huaqiu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.ResultType;
import org.huaqiu.dto.EmployeeLoginDTO;
import org.huaqiu.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.huaqiu.service.EmployeeService;
import org.huaqiu.result.*;
import org.huaqiu.entity.*;
import org.huaqiu.dto.*;
import org.huaqiu.vo.*;

@RestController
@RequestMapping("admin/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

//    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录{}",employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);
        return Result.success();
    }
    @GetMapping
    public void  HelloWorld(){
        System.out.println("HelloWorld");
    }

}
