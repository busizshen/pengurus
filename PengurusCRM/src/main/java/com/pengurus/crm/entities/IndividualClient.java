package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.enums.UserRole;

public class IndividualClient extends Client{

	private static final long serialVersionUID = -4250995438524672861L;

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
