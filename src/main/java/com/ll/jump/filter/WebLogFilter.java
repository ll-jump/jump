package com.ll.jump.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
//该注解等同于 com.ll.jump.config.WebLogFilterConfig
//@Component  //启动类添加@ServletComponentScan则不需该注解
//@Order(1)
//@WebFilter(urlPatterns = {"/*"}, filterName = "webLogFilter")
public class WebLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String requestId = UUID.randomUUID().toString().replace("-", "");
        MDC.put("sessionId", httpRequest.getSession().getId());
        MDC.put("requestId", requestId);
        String method = httpRequest.getMethod();
        String url = httpRequest.getRequestURI();
        LOGGER.info("url:【{}】,method:【{}】 begin", url, method);
        try {
            chain.doFilter(request, response);
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("url:【{}】,method:【{}】,cost:【{}ms】 end", url, method, end - start);
            MDC.remove("sessionId");
            MDC.remove("requestId");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
