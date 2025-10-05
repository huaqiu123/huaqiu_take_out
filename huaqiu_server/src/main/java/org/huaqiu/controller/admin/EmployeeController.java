package org.huaqiu.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.ResultType;
import org.huaqiu.constant.JwtClaimsConstant;
import org.huaqiu.dto.EmployeeLoginDTO;
import org.huaqiu.properties.properties.JwtProperties;
import org.huaqiu.utils.JwtUtil;
import org.huaqiu.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.huaqiu.service.EmployeeService;
import org.huaqiu.result.*;
import org.huaqiu.entity.*;
import org.huaqiu.dto.*;
import org.huaqiu.vo.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProperties jwtProperties;

//    private JwtProperties jwtProperties;
    @ApiOperation(value = "员工登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录{}",employeeLoginDTO);
        System.out.println(employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        Map<String,Object> claims = new HashMap<>();

        claims.put(JwtClaimsConstant.EMP_ID,employee.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @PostMapping
    @ApiOperation(value = "添加员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        System.out.println(employeeDTO);
        employeeService.save(employeeDTO);

//        employeeService.save();
        return Result.success();
    }
    @GetMapping("page")
    public Result page( EmployeePageDTO employeePageDTO){

        System.out.println(employeePageDTO);

        PageResult pageResult = employeeService.pageQuery(employeePageDTO);

        return Result.success(pageResult);
    }


}
