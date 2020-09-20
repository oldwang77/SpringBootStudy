package com.kuang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// AOP:拦截器
// 加上注解，这个类已经被spring托管了
@EnableWebSecurity  // 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人都可以访问，功能也只有对应有权限的人才能访问到
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()               // 首页所有人都可以访问
                .antMatchers("/level1/**").hasRole("vip1")  // VIP1才能访问
                .antMatchers("/level2/**").hasRole("vip2")  // vip2才能访问
                .antMatchers("/level3/**").hasRole("vip3"); // vip3才能访问

        // 没有权限默认会到登录页面,需要开启登录的页面
        // forLogin跳转到login页面
        /**
         *
         * .successForwardUrl("/index") // 登入成功后，跳转至指定页面
         * .defaultSuccessUrl("/index")   // 访问指定页面，用户未登入，跳转至登入页面，如果登入成功，跳转至用户访问指定页面，用户访问登入页面，默认的跳转页面
         *
         @Override
         protected void configure(HttpSecurity http) throws Exception {
         //  允许所有用户访问"/"和"/index.html"
         http.authorizeRequests()
         .antMatchers("/", "/index.html").permitAll()
         .anyRequest().authenticated()   // 其他地址的访问均需验证权限
         .and()
         .formLogin()
         .loginPage("/login.html")   //  登录页
         //.successForwardUrl("/index") // 登入成功后，跳转至指定页面
         .defaultSuccessUrl("/index")   // 访问指定页面，用户未登入，跳转至登入页面，如果登入成功，跳转至用户访问指定页面，用户访问登入页面，默认的跳转页面
         .failureUrl("/login-error.html").permitAll()
         .and()
         .logout()
         .logoutSuccessUrl("/index.html");
         }
         */

        http.formLogin()                            // 表单登陆
                .usernameParameter("username")      // 表单提交username
                .passwordParameter("password")      // 表单提交password
                .loginPage("/toLogin")              // 自定义登录页url,默认为/login
                .loginProcessingUrl("/login");      // 登录请求拦截的url,也就是form表单提交时指定的action

        //注销,开启了注销功能,跳到首页
        http.logout().logoutSuccessUrl("/");

        // 防止网站工具：get，post
        http.csrf().disable();//关闭csrf功能，登录失败肯定存在的原因

        //开启记住我功能: cookie,默认保存两周,自定义接收前端的参数
        http.rememberMe().rememberMeParameter("remember");
    }

    // 认证，springboot 2.1.x 可以直接使用
    // 密码编码： PasswordEncoder
    // 在spring Secutiry 5.0+ 新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据正常应该中数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("kuangshen").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
