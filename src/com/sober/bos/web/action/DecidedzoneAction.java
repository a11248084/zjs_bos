package com.sober.bos.web.action;

import com.sober.bos.domain.Customer;
import com.sober.bos.domain.Decidedzone;
import com.sober.bos.service.base.CustomerService;
import com.sober.bos.web.action.base.BaseAction;
import com.sober.bos.web.action.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/3.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

//使用属性封装的方法封装subarea的id  首选要在subarea的实体类中加入 getsubarea()的方法

    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String pageQuery(){
        decidedzoneService.pageQuery(pageBean);
        String[] excludes=new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"};
        writePageBean2Json(pageBean, excludes);
        return NONE;
    }

    //保存的方法
    public String save(){
        decidedzoneService.save(model,subareaid);
        return "list";
    }



    @Resource
    private CustomerService customerService;
    public String findCustomersNoAssociation(){
        //代理对象指定的crm服务发送远程调用请求
        List<Customer> list=customerService.findnoassociationCustomers();
        //排除不要序列化的字段
        String[] excludes=new String[]{"station","telephone","address","decidedzone_id"};
        //写会数据
        System.out.println(list+"=========================================");
        writeList2Json(list,excludes);
        return NONE;
    }

    public String findCustomersAssociation(){
        //代理对象指定的crm服务发送远程调用请求
        List<Customer> list=customerService.findhasassociationCustomers(model.getId());
        //排除不要序列化的字段
        String[] excludes=new String[]{"station","telephone","address","decidedzone_id"};
        //写会数据
        System.out.println(list+"=========================================");
        writeList2Json(list,excludes);
        return NONE;
    }



    /**
     * 通过代理对象调用crm服务，将客户关联到指定定区
     */
    //使用属性封装接收传过来的id数组
    private Integer[] customerIds;

    public void setCustomerIds(Integer[] customerIds) {
        this.customerIds = customerIds;
    }

    public String assigncustomerstodecidedzone(){
        customerService.assignCustomersToDecidedZone(customerIds,model.getId());
        return "list";
    }


}

