package com.szsd.demo.shirotest.shiro;

import com.szsd.demo.shirotest.util.result.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

public class ShiroLogin {
    public static String login(UsernamePasswordToken token){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录验证
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return ResultGenerator.loginAccount().toString();
        } catch (IncorrectCredentialsException ice) {
            return ResultGenerator.loginPassword().toString();
        } catch (LockedAccountException lae) {
            return ResultGenerator.loginPassword().toString();
        } catch (ExcessiveAttemptsException eae) {
            return ResultGenerator.loginNumber().toString();
        } catch (AuthenticationException ae) {
            return ResultGenerator.loginLocked().toString();
        }
        if (subject.isAuthenticated()) {
            return ResultGenerator.loginSuccess().toString();
        } else {
            token.clear();
            return ResultGenerator.loginFail().toString();
        }
    }

    public static String[] MD5Pwd(String username, String pwd) {
        // 加密算法MD5 salt盐 username + salt  迭代次数
        String salt = username + new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        String md5Pwd = new SimpleHash("MD5", pwd,
                salt, 2).toHex();
        return new String[]{salt, md5Pwd};
    }
}
