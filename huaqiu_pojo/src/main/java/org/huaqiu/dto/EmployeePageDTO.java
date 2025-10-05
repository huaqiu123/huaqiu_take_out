package org.huaqiu.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageDTO implements Serializable {
    private String name;
    private int page;

    private int pageSize;

}
