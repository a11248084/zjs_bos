package com.itheima.bos.utils;

import com.itheima.bos.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Renhai on 2016/10/30.
 */
public class loginUser {
    public static User getLoginUser(){
        return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
    }
}
