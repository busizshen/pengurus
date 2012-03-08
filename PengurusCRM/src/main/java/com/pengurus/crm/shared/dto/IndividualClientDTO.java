package com.pengurus.crm.shared.dto;

import java.util.Set;


public class IndividualClientDTO extends ClientDTO {

	private PersonalDataDTO personalData;

    public IndividualClientDTO() {
        super();
    }

    public IndividualClientDTO(Long id, Set<UserRoleDTO> permission, String login,
                            String password, String description, 
                            PersonalDataDTO personalData) {
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
	public String getType() {
		return "Individual Client";
	}
	@Override
	public String getFullName() {
		return personalData.getFullName();
	}
    
}
