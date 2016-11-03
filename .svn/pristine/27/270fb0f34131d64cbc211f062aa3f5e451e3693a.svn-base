package com.itheima.bos.web.interceptor;

import com.itheima.bos.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Renhai on 2016/10/30.
 *
 *
 * 自定义拦截器  用于拦截用户的登录状态
 */
public class loginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user =  (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        if (user==null){
            //未登录
            return "login";
        }else {
            //已登录
            actionInvocation.invoke();
        }
        return null;
    }
}
