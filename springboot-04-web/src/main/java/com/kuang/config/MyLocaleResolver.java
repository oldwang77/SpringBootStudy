package com.kuang.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取请求
        // 模仿着源码来看
        String language = httpServletRequest.getParameter("l");
        Locale locale=Locale.getDefault();//如果没有就使用默认的
        // 如果请求链接携带了国际化参数
        // StringUtils==>thymeleaf
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String[] split = language.split("_");
            // 国家split[0]和地区split[1]
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
