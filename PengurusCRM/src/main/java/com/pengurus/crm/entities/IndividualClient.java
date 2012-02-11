package com.pengurus.crm.entities;

import java.util.Set;


public class IndividualClient extends Client{

	private PersonalData data;

    public IndividualClient() {
        super();
    }

    public IndividualClient(Set<UserRole> permission, String login,
                            String password, String description, 
                            PersonalData data) {
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
