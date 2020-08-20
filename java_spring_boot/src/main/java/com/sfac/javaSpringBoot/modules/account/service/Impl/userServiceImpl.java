package com.sfac.javaSpringBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.javaSpringBoot.modules.account.dao.UserDao;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.userServcie;
import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class userServiceImpl implements userServcie {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> insertUser(User user) {
        //根据要插入的user对象的名字先判断数据库中存不存在这个数据
        User userTemp=userDao.getUserByUserName(user.getUserName());
        if (userTemp!=null){
            return new Result<User>(
                    Result.ResultStatus.fAILD.status,"User name is repeat"
            );
        }
        //设置时间
        user.setCreateDate(LocalDateTime.now());
        //设置密码
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        //插入数据
        userDao.insertUser(user);
        return new Result<User>(
                Result.ResultStatus.SUCCESS.status,"insert success",user
        );
    }

    @Override
    public Result<User> login(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp!=null&& userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
            return new Result<>(Result.ResultStatus.SUCCESS.status,"success",userTemp);
        }
        return new Result<User>(Result.ResultStatus.fAILD.status,
                "UserName or password is error.");
    }


    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        //初始化分页，如果没有值才会有默认值
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }
}
