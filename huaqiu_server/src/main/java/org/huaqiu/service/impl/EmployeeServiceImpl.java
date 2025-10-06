package org.huaqiu.service.impl;
import com.github.pagehelper.PageHelper;
import java.util.List;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.huaqiu.constant.MessageConstant;
import org.huaqiu.constant.PasswordConstant;
import org.huaqiu.constant.StatusConstant;
import org.huaqiu.context.BaseContext;
import org.huaqiu.dto.EmployeeDTO;
import org.huaqiu.dto.EmployeeLoginDTO;
import org.huaqiu.dto.EmployeePageDTO;
import org.huaqiu.entity.Employee;
import org.huaqiu.exception.AccountLockException;
import org.huaqiu.exception.AccountNotFoundException;
import org.huaqiu.exception.PasswordErrorException;
import org.huaqiu.result.PageResult;
import org.huaqiu.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.huaqiu.mapper.*;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 实现用户service层
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;



    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);

        System.out.println(employee);

        if(employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        password= DigestUtils.md5DigestAsHex(password.getBytes());
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        employee.setCreateUser(2L);
        employee.setUpdateUser(2L);


        if(!password.equals(employee.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }


        if(employee.getStatus().equals(StatusConstant.DISABLE) ){

            throw new AccountLockException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;


    }

    @Override
    public void save(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword( DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.insert(employee);
    }

    @Override
    public PageResult pageQuery(EmployeePageDTO employeePageDTO) {
//        开始分页查询
        PageHelper.startPage(employeePageDTO.getPage(), employeePageDTO.getPageSize());

        Page<Employee> page = employeeMapper.pageQuery(employeePageDTO);

        long total = page.getTotal();
        List<Employee> records = page.getResult();

        return new PageResult(total, records);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = new Employee();
        employee.setStatus(status);
        employee.setId(id);
        employeeMapper.update(employee);


    }



}
