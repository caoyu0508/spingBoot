package com.sfac.springcloud.springCloudClientAccount.modules.account.service;


import com.github.pagehelper.PageInfo;
import com.sfac.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.sfac.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface userServcie {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    //删除
    Result<Object> deleteUser(int userId);

    //查询
    User getUserByUserId(int userId);

    Result<String> uploadUserImg(MultipartFile file);

    User getUserByUserName(String userName);

    void logout();
}
