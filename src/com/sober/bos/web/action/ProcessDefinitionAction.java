package com.sober.bos.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scoped;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by Renhai on 2016/11/12.
 */
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {


    //注入ProcessEngine
    @Resource
    private ProcessEngine processEngine;

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public String list(){
        ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
        List<ProcessDefinition> list = query.latestVersion().list();
        //把list放入值栈中
        ActionContext.getContext().getValueStack().set("list",list);
        return "list";
    }


    //定义一个变量接收上传的文件
    private File deploy;

    public void setDeploy(File deploy) {
        this.deploy = deploy;
    }


    public String deploy() throws FileNotFoundException {
        //部署流程定义
        DeploymentBuilder deployment = processEngine.getRepositoryService().createDeployment();
        InputStream in =new FileInputStream(deploy);
        deployment.addZipInputStream(new ZipInputStream(in));
        deployment.deploy();
        return "tolist";
    }




    //接收流程定义的id
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    //查看流程图的方法
    public String viewpng(){
        InputStream pngStream = processEngine.getRepositoryService().getProcessDiagram(id);
        //把这个流放入值栈中
        ActionContext.getContext().getValueStack().set("pngStream",pngStream);
        return "viewpng";
    }

}
