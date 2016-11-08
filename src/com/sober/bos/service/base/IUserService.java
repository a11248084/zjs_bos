package com.sober.bos.service.base;

import com.sober.bos.domain.User;

import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
public interface IUserService {
    public User login(User model);

    public void editPassword(String id,String password);

    public void save(User model, String[] roleIds);

   public List<User> findAll();

}
