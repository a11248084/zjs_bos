package com.sober.bos.service;

import com.sober.bos.dao.IRoleDao;
import com.sober.bos.dao.IUserDao;
import com.sober.bos.dao.UserDaoImpl;
import com.sober.bos.domain.Role;
import com.sober.bos.domain.User;
import com.sober.bos.service.base.IUserService;
import com.sober.bos.utils.MD5Utils;
import com.sober.bos.utils.loginUser;
import com.sober.bos.service.base.IUserService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
@Component
@Transactional
public class UserServiceImpl implements IUserService {


    @Resource
    private IUserDao userDao;

    @Resource
    private ProcessEngine processEngine;
    @Resource
    private IRoleDao roleDao;


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

    //同步数据到activiti框架中
    @Override
    public void save(User model, String[] roleIds) {
        String password = model.getPassword();
        password=MD5Utils.md5(password);
        model.setPassword(password);
        userDao.save(model);

        org.activiti.engine.identity.User user=new UserEntity();
        user.setId(model.getId());
        processEngine.getIdentityService().saveUser(user);

        //判断roleIds  防止空指针
       if(roleIds!=null &&roleIds.length>0){
           for (String id:roleIds){
               /*Role role=new Role();
               role.setId(id);*/
               Role role = roleDao.findById(id);
               model.getRoles().add(role);
               processEngine.getIdentityService().createMembership(user.getId(),role.getName());
           }
       }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(String[] ids) {
        if (ids!=null &ids.length>0){
            for (String id:ids){
                User byId = userDao.findById(id);
                userDao.delete(byId);
            }
        }
    }

    @Override
    public User findUsername(String username) {
        return userDao.findByUsername(username);
    }
}
