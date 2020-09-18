package com.kuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

// 如果你想自定义一些定制化的功能，只需要写这个组件，然后把它交给springboot，它会帮我们自动装配
// 扩展springmvc，所有的请求都会经过dispatchServlet
@Configuration
//@EnableWebMvc
// 这玩意就是导入了一个类，DelegatingWebMvcConfiguration，从容器中获取所有的webMvcConfig
// 如果有这个注解，自动装配就失效了，不能加这个注解

public class MyMvcConfig implements WebMvcConfigurer {

    // public interface ViewResolver 实现了视图解析器接口的类，我们就可以把他看作是视图解析器

    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    // 自定义了一个视图解析器
    public static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

    // 视图跳转
    // url访问kuang，结果跳转到test.html
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/kuang").setViewName("test");
    }
}
