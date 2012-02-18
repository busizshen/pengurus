package com.pengurus.crm.entities;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.enums.Status;
import com.pengurus.crm.server.UserDetailsServiceImpl;
import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.StatusDTOCreater;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class Quote {
    
    private Long id;
    private Status status;
    private Client client;
    private Worker supervisor;
    private Set<Job> jobs;
    private String description;

    public Quote() {
        super();
    }

    public Quote(Status status, Client client, Worker supervisor,
            Set<Job> jobs, String description) {
        super();
        this.status = status;
        this.client = client;
        this.supervisor = supervisor;
        this.jobs = jobs;
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Worker supervisor) {
        this.supervisor = supervisor;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    	
	
    public QuoteDTO toQuoteDTO() {
    	StatusDTO status = null;
    	ClientDTO client = null;
    	WorkerDTO supervisor = null;
    	if(this.status != null){
    		StatusDTOCreater statusDTO = new StatusDTOCreater();
    		String s = this.status.toString();
    		status = statusDTO.getStatusDTO(s);
    	}
    	if(this.client != null )
    		client = (ClientDTO) this.client.toUserDTOWithoutPassword();
    	if(this.supervisor != null)
    		supervisor = (WorkerDTO) this.supervisor.toUserDTOWithoutPassword();
		return new QuoteDTO(this.id,status,client,supervisor,null,this.description );
	}
}