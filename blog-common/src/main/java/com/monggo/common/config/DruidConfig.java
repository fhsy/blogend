package com.monggo.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  fhs
 * 2020/4/23 8:28
 * 文件说明:
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean//注册Servlet
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", ""); //允许所有访问
        // initParams.put("deny", ""); //拒绝访问
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean//注册filter
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");//放行
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Collections.singletonList("/*")); //拦截所有请求

        return bean;
    }
}
