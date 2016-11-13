package com.sober.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Renhai on 2016/11/5.
 */
public class Function {
    private String id;
    private Function parentFunction;
    private String name;
    private String code;
    private String description;
    private String page;
    private String generatemenu;
    private Integer zindex;
    private Set<Function> children =new HashSet<>();
    private Set<Role> roles =new HashSet<>();
    private String pId;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Function getParentFunction() {
        return parentFunction;
    }

    public void setParentFunction(Function parentFunction) {
        this.parentFunction = parentFunction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getGeneratemenu() {
        return generatemenu;
    }

    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu;
    }

    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public String getpId() {
        if (parentFunction!=null){
            return parentFunction.getId();
        }
        return "0";
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Set<Function> getChildren() {
        return children;
    }

    public void setChildren(Set<Function> children) {
        this.children = children;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
