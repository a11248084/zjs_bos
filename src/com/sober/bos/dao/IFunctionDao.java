package com.sober.bos.dao;

import com.sober.bos.dao.base.IBaseDao;
import com.sober.bos.domain.Function;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
public interface IFunctionDao extends IBaseDao<Function> {

    public List<Function> findByUserId(String uid);

    public List<Function> findAllMenu();


    public List<Function> findMenu(String id);

    public  List<Function> findParent();

    public  List<Function> findSysMenu();


}
