package com.kuang.Config;

import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 无论登陆哪一个用户都会有这个方法
        info.addStringPermission("user:add");

        // 拿到当前用户的信息
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();   // 拿到User对象
        // 设置当前用户的权限，从数据库里面的获取的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
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
