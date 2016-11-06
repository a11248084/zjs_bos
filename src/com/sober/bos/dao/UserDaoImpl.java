package com.sober.bos.dao;

import com.sober.bos.dao.base.BaseDaoImpl;
import com.sober.bos.domain.User;
import com.opensymphony.xwork2.ModelDriven;
import com.sober.bos.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
@Component
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


    @Override
    public User findByUsername(String username) {
        String hql="from User where username =?";
        List list = this.getHibernateTemplate().find(hql, username);
        if (list!=null &&list.size()>0){
            return (User) list.get(0);
        }
        return null;
    }
}
