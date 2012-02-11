package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserRoleDTO implements IsSerializable{

    private Long id;
    private String role;

    static public UserRoleDTO ROLE_USER = new UserRoleDTO("ROLE_USER");

    public UserRoleDTO() {

    }

    public UserRoleDTO(Long id) {
        this.id = id;
    }
    
    public UserRoleDTO(String role) {
        this.role = role;
    }
    
    public UserRoleDTO(Long id, String role) {
        this.id = id;
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
