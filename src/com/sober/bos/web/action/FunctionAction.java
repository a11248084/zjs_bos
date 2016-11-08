package com.sober.bos.web.action;

import com.sober.bos.domain.Function;
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
}
