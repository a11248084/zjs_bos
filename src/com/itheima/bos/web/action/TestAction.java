package com.itheima.bos.web.action;

import com.itheima.bos.domain.Customer;
import com.itheima.bos.service.base.CustomerService;
import com.itheima.bos.web.action.base.BaseAction;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Renhai on 2016/11/3.
 */
@Controller
@Scope("prototype")
public class TestAction extends BaseAction<Customer> {

    @Resource
    private CustomerService  customerService;

   public String findCustomer(){
       List<Customer> customers = customerService.findnoassociationCustomers();
       for (int i = 0; i <customers.size() ; i++) {
           System.out.println(customers.get(i).getName());
       }
       return NONE;
   }
}
