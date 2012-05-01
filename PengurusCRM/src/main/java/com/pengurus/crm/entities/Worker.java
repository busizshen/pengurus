package com.pengurus.crm.entities;

import java.util.Set;

import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;


public class Worker extends User {
    
	private PersonalData personalData;
	  
    public Worker() {
        super();
    }
    
    public Worker(Set<UserRoleDTO> permission, String login, String password,
                  String description, PersonalData personalData) {
        super(permission, login, password, description);
        this.personalData = personalData;
    }

    protected void init(WorkerDTO workerDTO) {
    	super.init(workerDTO);
    	this.personalData = new PersonalData(workerDTO.getPersonalData());
    }
    
    public Worker(WorkerDTO workerDTO) {
    	init(workerDTO);
	}

	public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    
    protected void rewrite(WorkerDTO workerDTO) {
    	super.rewrite(workerDTO);
    	workerDTO.setPersonalData(personalData.toDTO());
    }
    
    public WorkerDTO toDTO(){
    	WorkerDTO workerDTO = new WorkerDTO();
    	rewrite(workerDTO);
    	return workerDTO;
	}
    
    public WorkerDTO toDTOLazy(){
    	return toDTO();
	}
}
