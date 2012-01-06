package com.pengurus.entities;

import java.util.List;

import com.pengurus.enums.UserRole;

public abstract class User {
    
    private List<UserRole> permission;
    private String login;
    private String password;
    private String description;
     
    public User() {
        super();
    }
    
    public User(List<UserRole> permission, String login, String password,
                String description) {
        super();
        this.permission = permission;
        this.login = login;
        this.password = password;
        this.description = description;
    }


    public List<UserRole> getPermission() {
        return permission;
    }
    
    public void setPermission(List<UserRole> permission) {
        this.permission = permission;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
