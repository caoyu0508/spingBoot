package com.sfac.javaSpringBoot.config;

import com.sfac.javaSpringBoot.filter.RequestParamaFilter;
import com.sfac.javaSpringBoot.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 此类是为了把http协议也能使用，创建一个链接器，然后把它部署到tomcat中去，这样就能使用http协议和https了。
* */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {
    //注入拦截器
    @Autowired
    private RequestViewInterceptor requestViewInterceptor;

    @Value("${server.http.port}")
    private int httpPort;
    //生成一个连接器
    @Bean
    public Connector connector(){
        Connector connector = new Connector();
        connector.setPort(httpPort);
        connector.setScheme("http");
        return connector;
    }
//把连接器部署到tomcat中去
    @Bean
    public ServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector());
        return tomcat;
    }

    //注册过滤器到容器中去
    @Bean
    public FilterRegistrationBean<RequestParamaFilter> register(){
        FilterRegistrationBean<RequestParamaFilter> register=
                new FilterRegistrationBean<RequestParamaFilter>();
        register.setFilter(new RequestParamaFilter());
        return register;
    }

    //注册拦截器，实现父类接口后重写的方法，不再是一个bean了
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将所有的路径加入到拦截器中
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }
}
