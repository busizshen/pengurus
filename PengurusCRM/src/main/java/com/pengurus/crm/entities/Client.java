package com.pengurus.crm.entities;

import java.util.List;

import com.pengurus.crm.enums.UserRole;

public abstract class Client extends User {

	private static final long serialVersionUID = -764945155580666817L;

	public Client() {
        super();
    }

    public Client(List<UserRole> permission, String login, String password,
                  String description) {
        super(permission, login, password, description);
    }
 
    

}
