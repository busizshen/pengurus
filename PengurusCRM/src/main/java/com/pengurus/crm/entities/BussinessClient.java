package com.pengurus.crm.entities;

import java.util.List;
import java.util.Set;



public class BussinessClient extends Client {
    
	private String name;
    private List<PersonalData> agents;
    
    public BussinessClient() {
        super();
    }

    public BussinessClient(Set<UserRole> permission, String login,
                           String password, String description, String name,
                           List<PersonalData> agents) {
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

    public List<PersonalData> getAgents() {
        return agents;
    }

    public void setAgents(List<PersonalData> agents) {
        this.agents = agents;
    }

}
