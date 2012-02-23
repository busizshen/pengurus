package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;


public class IndividualClient extends Client{

	private PersonalData data;

    public IndividualClient() {
        super();
    }

    public IndividualClient(Set<UserRoleDTO> permission, String login,
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

	@Override
	public ClientDTO toDTO() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
