package com.sfac.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.service.userServcie;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sfac.javaSpringBoot.modules.account.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private userServcie userServcie;

    /**
     * 127.0.0.1/api/login ---- post
     * {"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user){
        return userServcie.login(user);
    }

    /**
     * 127.0.0.1/api/user ---- post
     * {"userName":"admin","password":"111111"}
     */
    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user){
        return userServcie.insertUser(user);
    }

    /**
     * 127.0.0.1/api/users----post
     * {"currentPage":"1","pageSize":"5","keyWord":"hu"}
     */
    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo){
        return userServcie.getUsersBySearchVo(searchVo);
    }
}
