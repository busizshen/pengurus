package com.pengurus.crm.entities;

import java.util.HashSet;
import java.util.Set;

import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class Project {

	private Long id;
	private Long quoteId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Client client;
	private Worker supervisor;
	private Set<Worker> projectManagers;
	private Set<Translator> experts;
	private Set<Translator> freelancers;
	private Set<Job> jobs;
	private String description;

	public Project() {
		super();
	}

	public Project(Long quoteId, Client client, Worker supervisor,
			Set<Worker> projectManagers, Set<Translator> experts,
			Set<Translator> freelancers, Set<Job> jobs, String description) {
		super();
		this.quoteId = quoteId;
		this.client = client;
		this.supervisor = supervisor;
		this.projectManagers = projectManagers;
		this.experts = experts;
		this.freelancers = freelancers;
		this.jobs = jobs;
		this.description = description;
	}

	public Project(ProjectDTO projectDTO) {
		super();
		if (projectDTO.getClient() != null) {
			if (projectDTO.getClient() instanceof BusinessClientDTO)
				this.client = new BusinessClient();
			else
				this.client = new IndividualClient();
			this.client.setId(projectDTO.getClient().getId());
		}
		this.quoteId = projectDTO.getQuoteId();
		this.description = projectDTO.getDescription();
		this.id = projectDTO.getId();
		this.supervisor = null;
		if (projectDTO.getSupervisor() != null) {
			this.supervisor = new Worker();
			this.supervisor.setId(projectDTO.getSupervisor().getId());
		}
		this.jobs = new HashSet<Job>();
		if (projectDTO.getJobs() != null) {
			for (JobDTO j : projectDTO.getJobs()) {
				Job job = new Job();
				job.setId(j.getId());
				this.jobs.add(job);
			}
		}
		if(projectDTO.getExperts()!= null){
			this.experts = new HashSet<Translator>();
			for(TranslatorDTO translatorDTO : projectDTO.getExperts()){
				this.experts.add(new Translator(translatorDTO));
			}
		}
		if(projectDTO.getProjectManagers()!= null){
			this.projectManagers = new HashSet<Worker>();
			for(WorkerDTO workerDTO : projectDTO.getProjectManagers()){
				this.projectManagers.add(new Worker(workerDTO));
			}
		}
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

	public Set<Worker> getProjectManagers() {
		return projectManagers;
	}

	public void setProjectManagers(Set<Worker> projectManagers) {
		this.projectManagers = projectManagers;
	}

	public Set<Translator> getExperts() {
		return experts;
	}

	public void setExperts(Set<Translator> experts) {
		this.experts = experts;
	}

	public Set<Translator> getFreelancers() {
		return freelancers;
	}

	public void setFreelancers(Set<Translator> freelancers) {
		this.freelancers = freelancers;
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

	public ProjectDTO toDTO() {
		ProjectDTO pDTO = this.toDTOLazy();
		for (Translator e : this.experts)
			pDTO.getExperts().add(e.toDTO());
		for (Translator f : this.freelancers)
			pDTO.getFreelancers().add(f.toDTO());
		for (Job j : this.jobs)
			pDTO.getJobs().add(j.toDTOLazy());
		for (Worker w : this.projectManagers)
			pDTO.getProjectManagers().add(w.toDTOLazy());
		return pDTO;
	}

	public ProjectDTO toDTOLazy() {
		ProjectDTO pDTO = new ProjectDTO();
		if (this.client != null)
			pDTO.setClient(this.client.toDTOLazy());
		pDTO.setDescription(this.description);
		pDTO.setId(this.id);
		pDTO.setQuoteId(quoteId);
		if (this.supervisor != null)
			pDTO.setSupervisor(this.supervisor.toDTOLazy());
		return pDTO;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public Long getQuoteId() {
		return quoteId;
	}
}
