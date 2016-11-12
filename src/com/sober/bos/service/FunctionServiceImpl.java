package com.sober.bos.service;

import com.sober.bos.dao.IFunctionDao;
import com.sober.bos.domain.Function;
import com.sober.bos.domain.User;
import com.sober.bos.service.base.IFunctionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Resource
    private IFunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }


    //根据登录人查询对应的权限菜单
    @Override
    public List<Function> findMenu(User loginUser) {
        List<Function> list =null;
        if(loginUser.getUsername().equals("admin")){
            //超级管理员  查询所有数据
          list= functionDao.findAllMenu();
        }else {
            //普通用户 查询对应的全线数据
            list =functionDao.findMenu(loginUser.getId());
        }
        return list;
    }

    @Override
    public List<Function> findParent() {
        return functionDao.findParent();
    }

    @Override
    public void save(Function model) {
        functionDao.save(model);//持久态对象
        String id = model.getParentFunction().getId();
        if (id!=null && id!=""){
            model.setpId(id);
        }
    }

    @Override
    public void delete(String id) {
        Function byId = functionDao.findById(id);
        if (byId!=null){
            functionDao.delete(byId);
        }
    }

    @Override
    public List<Function> findSysMenu(User loginUser) {
        return functionDao.findSysMenu();
    }
}
