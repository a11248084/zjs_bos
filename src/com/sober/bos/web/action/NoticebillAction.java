package com.sober.bos.web.action;

import com.sober.bos.domain.Customer;
import com.sober.bos.domain.Noticebill;
import com.sober.bos.domain.User;
import com.sober.bos.service.base.CustomerService;

import com.sober.bos.utils.loginUser;
import com.sober.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Renhai on 2016/11/4.
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
    //提供一个属性驱动封装telephone
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //注入代理对象
    @Resource
    private CustomerService customerService;
    public String findCustomerByPhone(){
        //调用service crm的远程接口
        Customer c = customerService.findCustomerByPhone(phone);
        String[] excludes=new String[]{"decidedzone_id"};
        writeObjcet2Json(c, excludes);
        return NONE;
    }


    //保存业务通知单的方法
    public String save(){
        User User = loginUser.getLoginUser();
        model.setUser(User);
        INoticebillService.save(model);
        return "toSave";
    }
}
