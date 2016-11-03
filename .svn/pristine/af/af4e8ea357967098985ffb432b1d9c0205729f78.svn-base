package com.itheima.bos.web.action;


import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserServiceImpl;
import com.itheima.bos.service.base.IUserService;
import com.itheima.bos.utils.loginUser;
import com.itheima.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Renhai on 2016/10/30.
 */

//使用注解开发实例化bean

@Repository
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    //属性驱动获取用户输入的验证码
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    //登录系统
    public String login() {
        //获取session域中存入的验证码的值
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //判断验证码是否正确或为空
        if (StringUtils.isBlank(checkcode) || !key.equals(checkcode)) {
            //验证码不正确
            this.addActionError(this.getText("checkCodeError"));
            return "login";
        } else {
            //验证码正确
            //验证用户是否正确  调用service层的方法
            User existUser = userService.login(model);
            if (existUser != null) {
                //把用户放入session域中
                request.getSession().setAttribute("loginUser", existUser);
                //跳转页面到主页
                return "home";
            } else {

                //提示信息 用户名或密码不正确
                this.addActionError(this.getText("loginError"));
                return "login";
            }
        }
    }

    //登出系统
    public String loginout() {
        //登出系统 使session失效
        request.getSession().invalidate();
        return "login";
    }


    //修改密码操作
    public String editPassword() throws IOException {
        String password = model.getPassword();
        String id = loginUser.getLoginUser().getId();
        //修改密码操作
        String flag = "1";
        try {
            userService.editPassword(id,password);
        } catch (Exception e) {
            flag = "0";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(flag);
        return NONE;
    }
}
