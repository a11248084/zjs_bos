package com.sober.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Renhai on 2016/11/5.
 */
public class AuthFunction {
    private String id;
    private String name;
    private String code;
    private String description;
    private String page;
    private String generatemenu;
    private Integer zindex;
    private String pid;
    private Set<AuthRole> authRoles=new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthFunction that = (AuthFunction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (generatemenu != null ? !generatemenu.equals(that.generatemenu) : that.generatemenu != null) return false;
        if (zindex != null ? !zindex.equals(that.zindex) : that.zindex != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (generatemenu != null ? generatemenu.hashCode() : 0);
        result = 31 * result + (zindex != null ? zindex.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }


    public Set<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(Set<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }
}
