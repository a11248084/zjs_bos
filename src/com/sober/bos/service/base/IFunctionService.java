package com.sober.bos.service.base;

import com.sober.bos.domain.Function;
import com.sober.bos.domain.User;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
public interface IFunctionService {
    public List<Function> findAll();

    public List<Function> findMenu(User loginUser);

    public List<Function> findParent();


    public void save(Function model);

    public void delete(String id);

    public List<Function> findSysMenu(User loginUser);

}
