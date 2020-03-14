package com.szsd.demo.shirotest.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @ResponseBody
    @RequestMapping("/show")
    @RequiresPermissions("user:list")
    public String showUser() {
        System.out.println("这是学生信息");
        return "这是学生信息";
    }





}
