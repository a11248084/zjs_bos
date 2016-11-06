package com.sober.bos.web.realm;

import com.sober.bos.dao.IUserDao;
import com.sober.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by Renhai on 2016/11/5.
 */
public class BosRealm extends AuthorizingRealm {
    @Resource
    private IUserDao userDao;
        //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken atToken) throws AuthenticationException {
        //把令牌抢转为usernamepasswordToken令牌
        UsernamePasswordToken upToken=(UsernamePasswordToken)atToken;
        //获取传递过来的令牌的信息
        String username = upToken.getUsername();
        //char[] password = upToken.getPassword();
        //调用dao层查询数据库 返回user对象
        User user=userDao.findByUsername(username);
        //判断user
        if (user==null){
            return  null; //即验证不通过
        }
        //创建要返回的认证信息对象
        AuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(), this.getClass().getSimpleName());
        return info;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
