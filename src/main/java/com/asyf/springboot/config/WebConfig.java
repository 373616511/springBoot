package com.asyf.springboot.config;

import com.asyf.springboot.listener.SessionFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean filterRegist() {
        System.err.println("注册filterRegist-----");
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new SessionFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
