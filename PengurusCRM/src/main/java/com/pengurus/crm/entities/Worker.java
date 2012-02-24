package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;


public class Worker extends User {
    
	private PersonalData personalData;
        
    public Worker() {
        super();
    }
    
    public Worker(Set<UserRoleDTO> permission, String login, String password,
                  String description, PersonalData personalData) {
        super(permission, login, password, description);
        this.personalData = personalData;
    }

    public Worker(WorkerDTO userDTO) {
    	init(userDTO);
    	this.personalData = new PersonalData(userDTO.getPersonalData());
	}

	public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
