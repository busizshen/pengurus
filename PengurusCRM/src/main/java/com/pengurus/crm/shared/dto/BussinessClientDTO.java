package com.pengurus.crm.shared.dto;

import java.util.HashSet;
import java.util.Set;

public class BussinessClientDTO extends ClientDTO{
	
    private Set<PersonalDataDTO> agents = new HashSet<PersonalDataDTO>();
    
    public BussinessClientDTO() {
        super();
    }

    public BussinessClientDTO(Long id, Set<UserRoleDTO> permission, String login,
                           String password, String description, String name,
                           Set<PersonalDataDTO> agents) {
        super(id, permission, login, password, description);
        this.agents = agents;
    }

    public Set<PersonalDataDTO> getAgents() {
        return agents;
    }

    public void setAgents(Set<PersonalDataDTO> agents) {
        this.agents = agents;
    }

	public void addAgent(PersonalDataDTO personalDataDTO) {
		this.agents.add(personalDataDTO);
		
	}

	@Override
	public String getType() {
		return "Bussines Client";
	}

}
