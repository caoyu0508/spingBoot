package com.sfac.javaSpringBoot.modules.test.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//将这个类注册为主键,让他受ioc容器的管理
@Component
@PropertySource("classpath:config/applicationTest.properties")
//下面的与配置文件一一对应，相当于一个JavaBean，与配置文件进行映射
@ConfigurationProperties(prefix ="com.qq")
public class ApplicationTest {
//    @Value("${server.port}")
    private int port;
//    @Value("${com.name}")
    private String name;
//    @Value("${com.age}")
    private int age;
//    @Value("${com.desc}")
    private String desc;
//    @Value("${com.random}")
    private String random;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
