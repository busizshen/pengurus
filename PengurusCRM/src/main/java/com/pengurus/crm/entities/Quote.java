package com.pengurus.crm.entities;

import java.util.HashSet;
import java.util.Set;

import com.pengurus.crm.enums.Status;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.ClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
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


	public Quote(QuoteDTO quoteDTO) {
		super();
		this.client = null;
		if(quoteDTO.getClient() != null){
			if(quoteDTO.getClient() instanceof BusinessClientDTO)
				this.client = new BusinessClient();
			else if(quoteDTO.getClient() instanceof IndividualClientDTO)
				this.client = new IndividualClient(); 
			this.client.setId(quoteDTO.getClient().getId());
		}
		this.description = quoteDTO.getDescription();
		this.id = quoteDTO.getId();
		this.supervisor = null;
		if(quoteDTO.getSupervisor() != null){
			this.supervisor = new Worker(); 
			this.supervisor.setId(quoteDTO.getSupervisor().getId());
		}
		this.status = null;
		if(quoteDTO.getStatus() != null){
			this.status = Status.toStatus(quoteDTO.getStatus());
		}
		this.jobs = new HashSet<Job>();
		if(quoteDTO.getJobs() != null){
			for(JobDTO j :quoteDTO.getJobs()){
				Job job = new Job();
				job.setId(j.getId());
				this.jobs.add(job);
			}
		}
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
    		status = this.status.toStatusDTO();
    	}
    	if(this.client != null )
    		client = this.client.toDTO();
    	if(this.supervisor != null)
    		supervisor = (WorkerDTO) this.supervisor.toUserDTOWithoutPassword();
		return new QuoteDTO(this.id,status,client,supervisor,null,this.description );
	}
}