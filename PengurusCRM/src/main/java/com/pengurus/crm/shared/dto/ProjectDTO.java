package com.pengurus.crm.shared.dto;

import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.enums.Status;

public class ProjectDTO implements IsSerializable {
	    private Long id;
	    private Status status;
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    private ClientDTO client;
	    private WorkerDTO supervisor;
	    private Set<WorkerDTO> projectManagers;
	    private Set<TranslatorDTO> experts;
	    private Set<TranslatorDTO> freelancers;
	    private Set<JobDTO> jobs;
	    private String description;

	    public ProjectDTO() {
	        super();
	    }

	    public ProjectDTO(Status status, ClientDTO client, WorkerDTO supervisor,
	                   Set<WorkerDTO> projectManagers, Set<TranslatorDTO> experts,
	                   Set<TranslatorDTO> freelancers, Set<JobDTO> jobs,
	                   String description) {
	        super();
	        this.status = status;
	        this.client = client;
	        this.supervisor = supervisor;
	        this.projectManagers = projectManagers;
	        this.experts = experts;
	        this.freelancers = freelancers;
	        this.jobs = jobs;
	        this.description = description;
	    }

	    public Status getStatus() {
	        return status;
	    }

	    public void setStatus(Status status) {
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

	    public Set<WorkerDTO> getProjectManagers() {
	        return projectManagers;
	    }

	    public void setProjectManagers(Set<WorkerDTO> projectManagers) {
	        this.projectManagers = projectManagers;
	    }

	    public Set<TranslatorDTO> getExperts() {
	        return experts;
	    }

	    public void setExperts(Set<TranslatorDTO> experts) {
	        this.experts = experts;
	    }

	    public Set<TranslatorDTO> getFreelancers() {
	        return freelancers;
	    }

	    public void setFreelancers(Set<TranslatorDTO> freelancers) {
	        this.freelancers = freelancers;
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

}
