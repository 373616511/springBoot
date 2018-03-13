package com.asyf.springboot.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter
public class SessionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("过滤器初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("过滤器执行");
        //放开执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
