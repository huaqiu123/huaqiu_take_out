package org.huaqiu.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeLoginVO implements Serializable {

    private Long id;

    private String userName;

    private String name;

    private String token;
}
