package com.itheima.bos.web.action;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Renhai on 2016/11/3.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {


    public String pageQuery(){
        decidedzoneService.pageQuery(pageBean);
        String[] excludes=new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"};
        writePageBean2Json(pageBean, excludes);
        return NONE;
    }

}

