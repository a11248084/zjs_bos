package com.sober.bos.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Renhai on 2016/11/5.
 */
public class AuthRole {
    private String id;
    private String name;
    private String code;
    private String description;
    private Collection<User> users=new HashSet<>();
    private Set<AuthFunction> functions=new HashSet<>();

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Set<AuthFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(Set<AuthFunction> functions) {
        this.functions = functions;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthRole authRole = (AuthRole) o;

        if (id != null ? !id.equals(authRole.id) : authRole.id != null) return false;
        if (name != null ? !name.equals(authRole.name) : authRole.name != null) return false;
        if (code != null ? !code.equals(authRole.code) : authRole.code != null) return false;
        if (description != null ? !description.equals(authRole.description) : authRole.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }



}
