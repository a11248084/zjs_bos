package com.sober.bos.web.realm;

import com.sober.bos.dao.IFunctionDao;
import com.sober.bos.dao.IUserDao;
import com.sober.bos.domain.Function;
import com.sober.bos.domain.User;
import com.sober.bos.utils.MD5Utils;
import com.sober.bos.utils.loginUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/5.
 */
public class BosRealm extends AuthorizingRealm {
    @Resource
    private IUserDao userDao;
    @Resource
    private IFunctionDao functionDao;
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
        System.out.println("授权执行了..............");
        //授权信息对象
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        //根据当前登录用户查询数据库,获得相应的权限
        //info.addStringPermission("staff");
       User user= (User) principalCollection.getPrimaryPrincipal();
        if(user.getUsername().equals("admin")){
            //如果是adimin
            List<Function> list = functionDao.findAll();
            for(Function function:list){
                info.addStringPermission(function.getCode());
            }
        }else {
            //如果不是超级管理员
            List<Function> list= functionDao.findByUserId(user.getId());
            for(Function function:list){
                info.addStringPermission(function.getCode());
            }
        }
        return info;
    }


}
