package com.sober.bos.web.action;

import com.sober.bos.domain.Function;
import com.sober.bos.utils.loginUser;
import com.sober.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Renhai on 2016/11/7.
 */

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    //查询所有权限的方法
    public String list(){
       List<Function> list= functionService.findAll();
        //序列化为json数据 然后返回
        writeList2Json(list, new String[]{"children","roles"});
        return NONE;
    }


    //根据登录用户查询对应的权限菜单数据
    public String findMenu(){
       List<Function> list = functionService.findMenu(loginUser.getLoginUser());
        writeList2Json(list, new String[]{"children","roles"});
        return NONE;
    }


    public String findSysMenu(){
        List<Function> list = functionService.findSysMenu(loginUser.getLoginUser());
        writeList2Json(list, new String[]{"children","roles"});
        return NONE;
    }

    //查询父功能节点
    public String findParent(){
        List<Function> list=functionService.findParent();
        System.out.println(list.size()+"................................");
        writeList2Json(list, new String[]{"parentFunction","children","roles"});
        return NONE;
    }


    //保存权限的方法
    public String save(){
        functionService.save(model);
        return "list";
    }


    //删除的方法
    public String delete(){
        functionService.delete(model.getId());
        return "list";
    }
}
