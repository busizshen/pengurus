package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.PersonalDataDTO;

public class PersonalDataModel extends BaseModel {
	private static final long serialVersionUID = -4488706226554941188L;

	private PersonalDataDTO personalDataDTO;

	public PersonalDataModel(PersonalDataDTO personalDataDTO) {
		setPersonalDataDTO(personalDataDTO);
	}
	
	public PersonalDataDTO getPersonalDataDTO() {
		return personalDataDTO;
	}

	protected void setPersonalDataDTO(PersonalDataDTO personalDataDTO) {
		if (personalDataDTO.getId() != null) {
			set("id", personalDataDTO.getId());
		}
		if (personalDataDTO.getFirstName() != null) {
			set("firstName", personalDataDTO.getFirstName());
		}
		if (personalDataDTO.getLastName() != null) {
			set("lastName", personalDataDTO.getLastName());
		}
		if (personalDataDTO.getAddress() != null) {
			set("address", personalDataDTO.getAddress());
		}
		this.personalDataDTO = personalDataDTO;
	}
}
