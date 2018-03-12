package com.asyf.springboot.listener;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

public class SessionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("过滤器初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("过滤器执行");
    }

    public void destroy() {

    }
}
