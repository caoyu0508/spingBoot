package com.sfac.javaSpringBoot.config.shiro;

import com.sfac.javaSpringBoot.modules.account.entity.Resource;
import com.sfac.javaSpringBoot.modules.account.entity.Role;
import com.sfac.javaSpringBoot.modules.account.entity.User;
import com.sfac.javaSpringBoot.modules.account.service.ResourceService;
import com.sfac.javaSpringBoot.modules.account.service.userServcie;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.util.List;

@Component
public class MyRealm  extends AuthorizingRealm {
    @Autowired
    private userServcie userService;
    @Autowired
    private ResourceService resourceService;
    //资源授权
    //PrincipalCollection 主要的资源收集器
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //得到登录的用户信息
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                //AuthorizationInfo接口的实现类里面的方法，这里装好了角色信息
                simpleAuthorizationInfo.addRole(item.getRoleName());
                //根据角色查资源
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    //一种遍历的方式
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }

        return simpleAuthorizationInfo;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据taken得到重要的信息登录的用户名
        String userName= (String) token.getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user==null){
            throw new UnknownAccountException("The account do not exist.");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
