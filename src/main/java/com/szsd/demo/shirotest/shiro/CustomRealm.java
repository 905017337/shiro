package com.szsd.demo.shirotest.shiro;

import com.szsd.demo.shirotest.Dao.LoginDao;
import com.szsd.demo.shirotest.mapper.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


public class CustomRealm extends AuthorizingRealm {


    private static final transient Logger log = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private LoginDao loginDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("------获取用户身份信息------");

        User username = (User)SecurityUtils.getSubject().getPrincipal();
        Set<String> rolesList =  loginDao.findPermissionsByUser(username.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(rolesList);
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        User user1 = new User();
        user1.setName(userName);
        user1.setPassword(userPwd);
        //数据库获取用户资料
        User user = loginDao.findUserInfo(userName);
        if (user==null) {
            throw new UnknownAccountException("用户名不正确");
        }
        String password = user.getPassword();
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user1, password,
                ByteSource.Util.bytes(user.getSalt()), getName());
    }

}
