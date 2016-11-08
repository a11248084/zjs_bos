package com.sober.bos.web.action;

import com.sober.bos.domain.Region;
import com.sober.bos.domain.Role;
import com.sober.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {


    //属性封装接收functionIds
    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    //保存角色的方法
    public String save(){
        roleService.save(model,functionIds);
        return "list";
    }

    //查询所有角色
    public String findAll(){
            List<Role> list= roleService.findAll();
        this.writeList2Json(list, new String[]{"users","functions"});
        return NONE;
    }

    //查询所有
    public String list(){
        List<Role> list = roleService.findAll();
        this.writeList2Json(list, new String[]{"users","functions"});
        return NONE;
    }
}
