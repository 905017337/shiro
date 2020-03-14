package com.szsd.demo.shirotest.serviceImpl;

import com.szsd.demo.shirotest.Dao.LoginDao;
import com.szsd.demo.shirotest.mapper.User;
import com.szsd.demo.shirotest.service.LoginService;
import com.szsd.demo.shirotest.shiro.ShiroLogin;
import com.szsd.demo.shirotest.util.result.ResultGenerator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao logindao;
    @Override
    public String register(String username,String password) {

        String[] strings = ShiroLogin.MD5Pwd(username,password);

        String account = logindao.findUsername(username);
        if(account==null ){
            User user = new User();
            user.setName(username);
            user.setSalt(strings[0]);
            user.setPassword(strings[1]);

            Boolean save = logindao.saveUser(user);
            if(save){
                return ResultGenerator.registerSuccess().toString();
            }else {
                return ResultGenerator.registerFAIL().toString();
            }
        }else{
            return ResultGenerator.registerAccount().toString();
        }


    }

    @Override
    public String login(UsernamePasswordToken token) {


        return ShiroLogin.login(token);
    }
}
