package com.pengurus.crm.entities;

import java.util.List;

import com.pengurus.crm.enums.UserRole;

public class Worker extends User {
    
	private static final long serialVersionUID = -8500283670801168363L;
	
	private PersonalData data;
        
    public Worker() {
        super();
    }
    
    public Worker(List<UserRole> permission, String login, String password,
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
