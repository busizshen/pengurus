package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.BussinessClientDTO;
import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class BussinessClient extends Client {
    
	private String name;
    private Set<PersonalData> agents;
    
    public BussinessClient() {
        super();
    }

    public BussinessClient(Set<UserRoleDTO> permission, String login,
                           String password, String description, String name,
                           Set<PersonalData> agents) {
        super(permission, login, password, description);
        this.name = name;
        this.agents = agents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PersonalData> getAgents() {
        return agents;
    }

    public void setAgents(Set<PersonalData> agents) {
        this.agents = agents;
    }

	@Override
	public ClientDTO toDTO() {
		BussinessClientDTO bcDTO = new BussinessClientDTO();
		bcDTO.setId(this.getId());
		bcDTO.setUsername(this.getUsername());
		bcDTO.setDescription(this.getDescription());
		for(PersonalData pd : agents){
			bcDTO.addAgent(pd.toPersonalDataDTO());
		}
		for(UserRoleDTO a : this.getAuthorities()){
			this.getAuthorities().add(a);
		}
		return bcDTO;
	}

}
