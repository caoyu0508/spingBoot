package com.sfac.springcloud.springCloudClientAccount.modules.account.controller;


import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.sfac.springcloud.springCloudClientAccount.modules.account.service.userServcie;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private userServcie userServcie;

    /**
     * 127.0.0.1/api/user/1---get
     */
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){

        return userServcie.getUserByUserId(userId);
    }


}
