package com.sober.bos.service;

import com.sober.bos.dao.IRoleDao;
import com.sober.bos.domain.Function;
import com.sober.bos.domain.Role;
import com.sober.bos.service.base.IRoleService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
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

    @Resource
    private ProcessEngine processEngine;

    //同步流程数据  将bos中的用户和角色数据同步到activiti框架的表中
    @Override
    public void save(Role model,String functionIds) {
            roleDao.save(model);
            Group group=new GroupEntity();
            group.setId(model.getName());
            processEngine.getIdentityService().saveGroup(group);
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

    @Override
    public void delete(String ids) {
        if (ids!=null & !ids.equals("")){
            String[] split = ids.split(",");
            for (String id:split){
                Role byId = roleDao.findById(id);
                if (byId!=null){
                 roleDao.delete(byId);
                }
            }

        }
    }
}
