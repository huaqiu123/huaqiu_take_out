package org.huaqiu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.huaqiu.constant.StatusConstant;
import org.huaqiu.dto.EmployeePageDTO;
import org.huaqiu.entity.*;
import org.huaqiu.result.PageResult;
import com.github.pagehelper.Page;
@Mapper
public interface EmployeeMapper {
    Employee getByUsername(String username);
    void insert(Employee employee);
    Page<Employee> pageQuery(EmployeePageDTO employeePageDTO);
    void update(Employee employee);
    Employee getById(Long id);

}
