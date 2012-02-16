package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.UserRoleDTO;


public class Worker extends User {
    
	private PersonalData data;
        
    public Worker() {
        super();
    }
    
    public Worker(Set<UserRoleDTO> permission, String login, String password,
                  String description, PersonalData data) {
        super(permission, login, password, description);
        this.data = data;
    }

    public PersonalData getData() {
        return data;
    }

    public void setData(PersonalData data) {
        this.data = data;
    }
}
