package com.szsd.demo.shirotest.controller;

import com.szsd.demo.shirotest.service.LoginService;
import org.apache.shiro.authc.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeIndexController {

    private static final transient Logger log = LoggerFactory.getLogger(HomeIndexController.class);

    @Autowired
    private LoginService loginService;
    /**
     * 拦截后跳转的请求页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页1";
    }

    /**
     * 使用用户名和密码进行注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("username") String username, @RequestParam("password") String password){
        return  loginService.register(username, password);
    }

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        return loginService.login(token);
    }






}
