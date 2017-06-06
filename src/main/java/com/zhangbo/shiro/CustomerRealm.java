package com.zhangbo.shiro;

import com.zhangbo.model.User;
import com.zhangbo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by zhangbo on 2017/6/5.
 */
public class CustomerRealm extends AuthorizingRealm {

    private static final Logger loger = LoggerFactory.getLogger(CustomerRealm.class);

    private UserService userService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo authenticationInfo = null;
        try {
            User user = userService.findUserByUserName(username);
            if (user != null) {
                authenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            }
        } catch (Exception e) {
            loger.error("查询用户认证信息异常", e);
        }
        return authenticationInfo;
    }


    @Override
    public void setName(String name) {
        super.setName("customerRealm");
    }
}
