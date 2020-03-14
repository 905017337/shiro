package com.szsd.demo.shirotest.mapper;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String per;
    private String salt;
}
