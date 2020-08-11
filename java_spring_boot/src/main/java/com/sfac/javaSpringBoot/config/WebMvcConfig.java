package com.sfac.javaSpringBoot.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 此类是为了把http协议也能使用，创建一个链接器，然后把它部署到tomcat中去，这样就能使用http协议和https了。
* */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig {
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
}
