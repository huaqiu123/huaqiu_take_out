package org.huaqiu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.huaqiu.constant.StatusConstant;
import org.huaqiu.entity.*;

@Mapper
public interface EmployeeMapper {

    Employee getByUsername(String username);

    void insert(Employee employee);





}
