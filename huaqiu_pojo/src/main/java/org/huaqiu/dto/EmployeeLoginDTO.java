package org.huaqiu.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.Serializable;

@Data
@ApiModel(description = "员工登录时候传递的数据模型")
public class EmployeeLoginDTO implements Serializable {

    private String username;

    private String password;

}
