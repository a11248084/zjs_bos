package com.itheima.bos.service;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.dao.UserDaoImpl;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.base.IUserService;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.utils.loginUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
@Component
public class UserServiceImpl implements IUserService{


    @Resource
    private IUserDao userDao;

    @Override
    public User login(User model) {
        String password = MD5Utils.md5(model.getPassword());
        List list = userDao.findByNamedQuery("loginQuery", model.getUsername(), password);
        if (list!=null && list.size()>0){
            return (User) list.get(0);
        }
        return null;
    }

    @Override
    public void editPassword(String id, String password) {
        //修改密码
        password=MD5Utils.md5(password);
        userDao.executeNamedQuery("editPassword",password,id);
    }
}
