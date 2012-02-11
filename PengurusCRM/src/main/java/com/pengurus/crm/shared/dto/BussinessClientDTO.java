package com.pengurus.crm.shared.dto;

import java.util.List;
import java.util.Set;

public class BussinessClientDTO extends ClientDTO{
	
	private String name;
    private List<PersonalDataDTO> agents;
    
    public BussinessClientDTO() {
        super();
    }

    public BussinessClientDTO(Long id, Set<UserRoleDTO> permission, String login,
                           String password, String description, String name,
                           List<PersonalDataDTO> agents) {
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

    public List<PersonalDataDTO> getAgents() {
        return agents;
    }

    public void setAgents(List<PersonalDataDTO> agents) {
        this.agents = agents;
    }

}
