package com.sober.bos.web.action;


import com.sober.bos.domain.User;
import com.sober.bos.service.UserServiceImpl;
import com.sober.bos.service.base.IUserService;
import com.sober.bos.utils.MD5Utils;
import com.sober.bos.utils.loginUser;
import com.sober.bos.web.action.base.BaseAction;
import com.sober.bos.domain.User;
import com.sober.bos.utils.loginUser;
import com.sober.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
            try {
                //使用shiro提供的方法进行权限的盐城
                //验证用户和密码  获取当前用户对象  状态 为未认证状态
                Subject subject = SecurityUtils.getSubject();
                String username = model.getUsername();
                String password = model.getPassword();
                password= MD5Utils.md5(password);
                AuthenticationToken token = new UsernamePasswordToken(username, password);
                //调用安全管理器  安全管理器调用 realm   自定义一个realm
                subject.login(token);
                User user = (User) subject.getPrincipal();
                //把user放入session中
                request.getSession().setAttribute("loginUser", user);
            }catch (UnknownAccountException e){
                addActionError(getText("用户不存在!"));
                return "login";
            }catch (IncorrectCredentialsException e){
                addActionError(getText("密码错误!"));
                return "login";
            }
            return "home";
        }
    }

    //登录系统
    public String login_cp() {
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

    //属性驱动封装roleIds
    private  String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    //保存用户的操作
    public String save(){
    userService.save(model,roleIds);
    return "list";
    }

    //查询的的功能
    public String list(){
         List<User> list= userService.findAll();
        writeList2Json(list, new String[]{"noticebills","roles"});
        return NONE;
    }
}
