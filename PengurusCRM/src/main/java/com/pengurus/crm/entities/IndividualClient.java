package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;


public class IndividualClient extends Client {

	private PersonalData personalData;

    public IndividualClient() {
        super();
    }

    public IndividualClient(IndividualClientDTO individualClientDTO) {
    	super.init(individualClientDTO);
    	this.personalData = new PersonalData(individualClientDTO.getPersonalData());
    }
    
    public IndividualClient(Set<UserRoleDTO> permission, String login,
                            String password, String description, 
                            PersonalData personalData) {
        super(permission, login, password, description);
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
	public IndividualClientDTO toDTO() {
    	return this.toDTOLazy();
    }
    
    @Override
	public IndividualClientDTO toDTOLazy() {
		IndividualClientDTO bcDTO = new IndividualClientDTO();
		bcDTO.setId(this.getId());
		bcDTO.setUsername(this.getUsername());
		bcDTO.setDescription(this.getDescription());
		if(this.personalData != null)
			bcDTO.setPersonalData(this.personalData.toDTO());
		for(UserRoleDTO a : this.getAuthorities()){
			bcDTO.getAuthorities().add(a);
		}
		return bcDTO;
	}
	
	
    
    
}
