package com.itheima.bos.service.base;

import com.itheima.bos.domain.User;

/**
 * Created by Renhai on 2016/10/30.
 */
public interface IUserService {
    public User login(User model);

    public void editPassword(String id,String password);

}
