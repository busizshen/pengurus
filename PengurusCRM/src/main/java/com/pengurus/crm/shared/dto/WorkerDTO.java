package com.pengurus.crm.shared.dto;

import java.util.Set;


public class WorkerDTO extends UserDTO {
	
	private PersonalDataDTO personalData;
        
    public WorkerDTO() {
        super();
    }
    
    public WorkerDTO(Long id, Set<UserRoleDTO> permission, String login, String password,
                  String description, PersonalDataDTO personalData) {
        super(id, permission, login, password, description);
        this.personalData = personalData;
    }

    public PersonalDataDTO getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataDTO personalData) {
        this.personalData = personalData;
    }
    
    @Override
    public String getFullName() {
    	return getPersonalData().getFullName();
    }
}
