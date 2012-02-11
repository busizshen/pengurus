package com.pengurus.crm.shared.dto;

import java.util.Set;


public class IndividualClientDTO extends ClientDTO {

	private PersonalDataDTO data;

    public IndividualClientDTO() {
        super();
    }

    public IndividualClientDTO(Long id, Set<UserRoleDTO> permission, String login,
                            String password, String description, 
                            PersonalDataDTO data) {
        super(id, permission, login, password, description);
        this.data = data;
    }

    public PersonalDataDTO getData() {
        return data;
    }

    public void setData(PersonalDataDTO data) {
        this.data = data;
    }
    
}
