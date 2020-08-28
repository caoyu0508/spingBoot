package com.sfac.springcloud.springCloudClientAccount.modules.account.service.impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.sfac.springcloud.springCloudClientAccount.modules.account.dao.UserDao;
import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.sfac.springcloud.springCloudClientAccount.modules.account.service.userServcie;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements userServcie {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    @Transactional
    public Result<User> insertUser(User user) {
      /*  //根据要插入的user对象的名字先判断数据库中存不存在这个数据
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

        //删除新插入的数据的id在中间表里的数据
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            //以下是三种不同的遍历方式插入中间表的数据
           *//* for (int i = 0; i < roles.size(); i++) {
                userRoleDao.insertUserRole(user.getUserId(),roles.get(i).getRoleId());
            }*//*
            *//*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*//*
            //新的一种写法
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }*/

        return new Result<User>(
                Result.ResultStatus.SUCCESS.status, "Insert success.", user);
    }

    @Override
    public Result<User> login(User user) {

//        Subject subject = SecurityUtils.getSubject();
//        //包装令牌
//        UsernamePasswordToken usernamePasswordToken =
//                new UsernamePasswordToken(user.getUserName(),
//                        MD5Util.getMD5(user.getPassword()));
//        usernamePasswordToken.setRememberMe(user.getRememberMe());
//
//        try {
//            //调用realm，包装身份验证器最后两种作对比
//            subject.login(usernamePasswordToken);
//            //资源授权
//            subject.checkRoles();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Result<User>(Result.ResultStatus.fAILD.status,
//                    "UserName or password is error.");
//        }
//
//        Session session = subject.getSession();
//        session.setAttribute("user", (User)subject.getPrincipal());

        return new Result<User>(Result.ResultStatus.SUCCESS.status,
                "Login success.", user);

    }

    @Override
    @Transactional
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        //初始化分页，如果没有值才会有默认值
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUsersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
       /* User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null&& userTemp.getUserId()!=user.getUserId()) {
            return new Result<User>(
                    Result.ResultStatus.fAILD.status, "User name is repeat"
            );
        }

        userDao.updateUser(user);

        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            //新的一种写法
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(), item.getRoleId());
            });
        }*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);

        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success");
    }

    //这个方法能调用另外一个微服务的api/cities/{countryId}查询方法
    @Override
    public User getUserByUserId(int userId) {
        User user=userDao.getUserByUserId(userId);
        //响应的类型：List.class，522为给它的可变参数countryId
        List<City> cities = Optional
                .ofNullable(restTemplate.getForObject(
                        "http://CLIENT-TEST/api/cities/{countryId}",
                        List.class, 522))
                .orElse(Collections.emptyList());
        user.setCities(cities);
        return user;
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
       /* if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.fAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.fAILD.status, "Upload failed.");
        }*/

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", "");
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void logout() {
        /*Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        //清除session的信息
        session.setAttribute("user", null);*/
    }

}
