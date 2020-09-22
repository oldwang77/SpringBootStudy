package com.kuang.Config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // 创建realm对象，需要自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // DefaultwebSecurityManage
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 关联userRealm
        // 这个userRealm是从spring中获取的，我们可以通过传参的方式
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro的内置过滤器
        /**
         * anon:无需认证就可以访问
         * authc：必须认证了才可以访问
         * user:必须拥有记住我功能才能用
         * perms：拥有对某个资源的权限才能访问
         * roles：拥有某个角色权限才能访问
         */

        // 拦截
        Map<String,String> filterMap = new LinkedHashMap<>();

        // 授权
        // 必须是user用户，并且拥有add权限，才可以访问
        // 默认登陆的用户没有这个权限
        filterMap.put("/user/add","perms[user:add]");

        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 如果不满足认证请求，会跳转到登陆页面
        bean.setLoginUrl("/toLogin");

        // 未授权的页面
        bean.setUnauthorizedUrl("/noauto");
        return bean;
    }


}
