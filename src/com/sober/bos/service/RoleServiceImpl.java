package com.sober.bos.service;

import com.sober.bos.dao.IRoleDao;
import com.sober.bos.domain.Function;
import com.sober.bos.domain.Role;
import com.sober.bos.service.base.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public void save(Role model,String functionIds) {
            roleDao.save(model);
        String[] split = functionIds.split(",");
        for(String id:split){
            Function function=new Function();
            function.setId(id);
            model.getFunctions().add(function);
        }
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
