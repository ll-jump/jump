package com.ll.jump.base.config;


import com.ll.jump.base.filter.WebLogFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebLogFilterConfig {

    @Bean
    public FilterRegistrationBean configureFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("webLogFilter");
        WebLogFilter webLogFilter = new WebLogFilter();
        bean.setFilter(webLogFilter);
        bean.setOrder(1);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/*");
        bean.setUrlPatterns(urlList);
        return bean;
    }

}
