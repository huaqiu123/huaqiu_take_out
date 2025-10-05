package org.huaqiu.service;

import org.springframework.stereotype.Service;
import org.huaqiu.entity.*;
import org.huaqiu.dto.*;


public interface EmployeeService {

    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

}

