package com.kuang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // 后台监控:web.xml, ServletRegistrationBean
    // 因为SpringBoot 内置了 servlet容器，所以没有web.xml，替代方法ServletRegistrationBean
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 后台需要有人登陆，账号、密码
        Map<String,String> initParameters = new HashMap<>();
        // 增加配置
        // 登陆的key是指定的 loginUsername和loginPassword
        initParameters.put("loginUsername","root");
        initParameters.put("loginPassword","admin");

        // 允许谁可以访问
        // 为空，表示任何人都可以访问
        initParameters.put("allow","");
        //禁止谁能访问
        //initParameters.put("kuangshen","192.168.1.2");

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }

    // filter过滤器
    @Bean
    public FilterRegistrationBean webStartFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParamters = new HashMap<>();
        // 这些东西不进行过滤
        // 这些参数可以通过点击setInitParamters进行查看
        initParamters.put("exclusions","*.js,/druid/*");

        bean.setInitParameters(initParamters);

        return bean;
    }


}
