package com.sober.bos.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scoped;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Renhai on 2016/11/12.
 */
@Controller
@Scope("prototype")
public class ProcessInstanceAction extends ActionSupport {

    
    @Resource
    private ProcessEngine processEngine;
    
    //查看正在运行的流程实例
    public String list(){
        ProcessInstanceQuery processInstanceQuery = processEngine.getRuntimeService().createProcessInstanceQuery();
        List<ProcessInstance> list = processInstanceQuery.list();
        ActionContext.getContext().getValueStack().set("list",list);
        return "list";
    }


    public String id;

    public void setId(String id) {
        this.id = id;
    }


    //动态获取实例变量
    public String findData() throws IOException {
        //通过实例id获取实例变量信息
        Map<String, Object> variables = processEngine.getRuntimeService().getVariables(id);
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(variables);
        return NONE;
    }

    //显示流程图和流程执行位置的方法  查询坐标  部署id png图片
    public String showPng(){
        //通过实例id查出实例对象
        ProcessInstance instance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(id).singleResult();
        //通过实例对象查出定义id
        String definitionId = instance.getProcessDefinitionId();
        //通过定义id查询定义对象
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(definitionId);
        //通过定义对象查询出部署的id 和 png的名称
        String deployId = processDefinition.getDeploymentId();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        //根据实例对象查出坐标
        String activityId = instance.getActivityId();
        //通过流程定义对象查找出活动类
        ActivityImpl activity = processDefinition.findActivity(activityId);
        int x = activity.getX();
        int y = activity.getY();
        int width = activity.getWidth();
        int height = activity.getHeight();
        //压入值栈
        ActionContext.getContext().getValueStack().set("x",x);
        ActionContext.getContext().getValueStack().set("y",y);
        ActionContext.getContext().getValueStack().set("height",height);
        ActionContext.getContext().getValueStack().set("width",width);
        ActionContext.getContext().getValueStack().set("deploymentId",deployId);
        ActionContext.getContext().getValueStack().set("imageName",diagramResourceName);
        return "showpng";
    }



    private String imageName;
    private String deploymentId;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    //显示流程信息图
    public String viewImage(){
        InputStream pngStream = processEngine.getRepositoryService().getResourceAsStream(deploymentId, imageName);
        ActionContext.getContext().getValueStack().set("pngStream",pngStream);
        return "showimage";
    }

}
