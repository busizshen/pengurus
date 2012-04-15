package com.pengurus.crm.entities;

import java.util.HashSet;
import java.util.Set;

import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class BusinessClient extends Client {
	
	private String name;
    private Set<PersonalData> agents;
    
    public BusinessClient() {
        super();
    }

    public BusinessClient(Set<UserRoleDTO> permission, String login,
                           String password, String description, String name,
                           Set<PersonalData> agents) {
        super(permission, login, password, description);
        this.name = name;
        this.agents = agents;
    }

    public BusinessClient(BusinessClientDTO user) {
		init(user);
		this.name = user.getName();
		this.agents = new HashSet<PersonalData>();
		for (PersonalDataDTO personalDataDTO: user.getAgents()) {
			this.agents.add(new PersonalData(personalDataDTO));
		}
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

    protected void rewrite(BusinessClientDTO businessClientDTO) {
    	super.rewrite(businessClientDTO);
		for(PersonalData pd : agents){
			businessClientDTO.addAgent(pd.toDTO());
		}
		for(UserRoleDTO a : this.getAuthorities()){
			businessClientDTO.getAuthorities().add(a);
		}
    }
    
	@Override
	public BusinessClientDTO toDTO() {
		BusinessClientDTO businessClientDTO = new BusinessClientDTO();
		rewrite(businessClientDTO);
		return businessClientDTO;
	}
	
	@Override
	public BusinessClientDTO toDTOLazy() {
		return toDTO();
	}

}
