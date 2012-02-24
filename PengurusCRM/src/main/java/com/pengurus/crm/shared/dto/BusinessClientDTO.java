package com.pengurus.crm.shared.dto;

import java.util.Set;

public class BusinessClientDTO extends ClientDTO {
	
	private String name;
	private Set<PersonalDataDTO> agents;
    
    public BusinessClientDTO() {
        super();
    }

    public BusinessClientDTO(Long id, Set<UserRoleDTO> permission, String login,
                           String password, String description, String name,
                           Set<PersonalDataDTO> agents) {
        super(id, permission, login, password, description);
        this.name = name;
        this.agents = agents;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
