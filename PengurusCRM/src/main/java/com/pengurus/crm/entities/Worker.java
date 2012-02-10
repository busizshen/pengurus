package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.User;
import com.pengurus.crm.shared.UserRole;

public class Worker extends User {
    
	private static final long serialVersionUID = -8500283670801168363L;
	
	private PersonalData data;
        
    public Worker() {
        super();
    }
    
    public Worker(Set<UserRole> permission, String login, String password,
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
