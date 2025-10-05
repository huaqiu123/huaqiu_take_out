package org.huaqiu.service.impl;

import org.huaqiu.dto.EmployeeLoginDTO;
import org.huaqiu.entity.Employee;
import org.huaqiu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        System.out.println("nihao");
        return null;
    }
}
