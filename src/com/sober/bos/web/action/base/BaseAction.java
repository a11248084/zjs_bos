package com.sober.bos.web.action.base;

import com.sober.bos.service.base.*;
import com.sober.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Renhai on 2016/10/30.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    //request
    protected  HttpServletRequest request= ServletActionContext.getRequest();
    //response
    protected HttpServletResponse response=ServletActionContext.getResponse();
    //声明对象
    protected T model;

    //使用注解方式注入属性
    @Resource
    protected IUserService userService;

    @Resource
    protected IStaffService staffService;

    @Resource
    protected IRegionService regionService;

    @Resource
    protected com.sober.bos.service.base.ISubareaService ISubareaService;

    @Resource
    protected IDecidedzoneService decidedzoneService;


    @Resource
    protected INoticebillService INoticebillService;
    //利用属性驱动获取分页工具条自动传递过来的page(当前页) 和rows(每页查询的条数)
    protected int page;
    protected int rows;

    //初始化一个pagebean模型
    protected PageBean pageBean=new PageBean();

    public void setPage(int page) {
        pageBean.setCurrentPage(page);//当前的页码
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);//每页显示的记录数
    }




    //在构造方法中动态或的实体类的类型,并通过反射实例化对象
    public BaseAction() {
        //获取父类类型
        ParameterizedType generciSuperClass = (ParameterizedType)this.getClass().getGenericSuperclass();
        //获取实际类型参数
        Type[] actualTypeArguments = generciSuperClass.getActualTypeArguments();
        //实体类型
        Class<T> aClass = (Class<T>) actualTypeArguments[0];

        //使用离线条件查询对象包装查询条件  用于通用的分页查询操作
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(aClass);
        pageBean.setDetachedCriteria(detachedCriteria);

        try {
            model= aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T getModel() {
        return model;
    }




    //向客户端写出json数据的方法 将PageBean对象序列化  并写回页面

    public void writePageBean2Json(PageBean pageBean,String[] excludes){
        //查询之后向页面返回数据  数据类型为json格式
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JSONObject jsonObject  = JSONObject.fromObject(pageBean, jsonConfig);
        String json = jsonObject.toString();
        //将json数据通过输出流写到客户端
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //写回list集合的json数据
    public void writeList2Json(List list, String[] excludes) {
        //查询之后向页面返回数据  数据类型为json格式
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        String json = jsonArray.toString();
        //将json数据通过输出流写到客户端
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        //写回object对象的json数据
    public void writeObjcet2Json(Object object, String[] excludes){
        //查询之后向页面返回数据  数据类型为json格式
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(excludes);
        JSONObject jsonObject  = JSONObject.fromObject(object,jsonConfig);
        String json = jsonObject.toString();
        //将json数据通过输出流写到客户端
        response.setContentType("text/json;charset=utf-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
