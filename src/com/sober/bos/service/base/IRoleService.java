package com.sober.bos.service.base;

import com.sober.bos.domain.Role;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
public interface IRoleService {
    public void save(Role model,String functionIds);

    public List<Role> findAll();
}
