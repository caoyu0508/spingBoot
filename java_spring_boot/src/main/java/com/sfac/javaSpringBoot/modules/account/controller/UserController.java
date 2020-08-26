package com.sfac.javaSpringBoot.modules.account.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.service.userServcie;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
    public PageInfo<User> getUsersBySearchVo(@RequestBody  SearchVo searchVo){
        return userServcie.getUsersBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/user--------put
     * {"userName":"hujiang1","userImg":"/aaa.jpg","userId":"4"}
     */
    @PutMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user){
        return userServcie.updateUser(user);
    }


    /**
     * 127.0.0.1/api/user/1
     */
    @DeleteMapping("/user/{userId}")
    @RequiresPermissions(value = "/api/user")
    public Result<Object> deleteUser(@PathVariable int userId){
        return userServcie.deleteUser(userId);
    }


    /**
     * 127.0.0.1/api/user/1---get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){
        return userServcie.getUserByUserId(userId);
    }

    /**
     * 127.0.0.1/api/userImg ---- post
     */
    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file) {
        return userServcie.uploadUserImg(file);
    }


}
