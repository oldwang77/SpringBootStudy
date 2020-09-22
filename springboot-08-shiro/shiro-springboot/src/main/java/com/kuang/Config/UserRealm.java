package com.kuang.Config;

import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证");
        // 用户名、密码====》数据库中去取
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // // 虚拟用户
        // String name = "root";
        // String password = "123456";
        // if(!userToken.getUsername().equals(name)){
        //     return null;
        // }

        // 连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            return null;
        }

        // 可以加密，MD5加密
        // 密码认证，shiro做
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
