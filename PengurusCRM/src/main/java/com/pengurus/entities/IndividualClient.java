package com.pengurus.entities;

import java.util.List;

import com.pengurus.enums.UserRole;

public class IndividualClient extends Client{
    
    private PersonalData data;

    public IndividualClient() {
        super();
    }

    public IndividualClient(List<UserRole> permission, String login,
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
