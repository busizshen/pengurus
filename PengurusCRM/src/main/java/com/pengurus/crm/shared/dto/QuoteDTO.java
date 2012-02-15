package com.pengurus.crm.shared.dto;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;


public class QuoteDTO implements IsSerializable{

	private StatusDTO status;
	    private ClientDTO client;
	    private WorkerDTO supervisor;
	    private Set<JobDTO> jobs;
	    private String description;

	    public QuoteDTO() {
	    }

	    public QuoteDTO(StatusDTO status, ClientDTO client, WorkerDTO supervisor,
	            Set<JobDTO> jobs, String description) {
	        super();
	        this.status = status;
	        this.client = client;
	        this.supervisor = supervisor;
	        this.jobs = jobs;
	        this.description = description;
	    }

	    public StatusDTO getStatus() {
	        return status;
	    }

	    public void setStatus(StatusDTO status) {
	        this.status = status;
	    }

	    public ClientDTO getClient() {
	        return client;
	    }
	    
	    public void setClient(ClientDTO client) {
	     	this.client = client;
	    }

	    public WorkerDTO getSupervisor() {
	        return supervisor;
	    }	    

	    public void setSupervisor(WorkerDTO supervisor) {
	    	this.supervisor = supervisor;
	    }

	    public Set<JobDTO> getJobs() {
	        return jobs;
	    }

	    public void setJobs(Set<JobDTO> jobs) {
	        this.jobs = jobs;
	    }

	    public String getDescription() {
	        return description;
	    }
	    

	    public void setDescription(String description) {
	        this.description = description;
	    }

		public Object get(String property) {
			// TODO Auto-generated method stub
			return null;
		}
}
