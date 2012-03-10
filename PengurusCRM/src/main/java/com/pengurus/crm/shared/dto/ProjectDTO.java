package com.pengurus.crm.shared.dto;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.pengurus.crm.client.panels.center.project.ProjectPanelView;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;

public class ProjectDTO implements IsSerializable {
	    private Long id;
	    private StatusDTO status;
	    private ClientDTO client;
	    private WorkerDTO supervisor;
	    private Set<WorkerDTO> projectManagers = new HashSet<WorkerDTO>();
	    private Set<TranslatorDTO> experts = new HashSet<TranslatorDTO>();
	    private Set<TranslatorDTO> freelancers = new HashSet<TranslatorDTO>();
	    private Set<JobDTO> jobs = new HashSet<JobDTO>();
	    private String description;

	    public ProjectDTO() {
	        super();
	    }

	    public ProjectDTO(StatusDTO status, ClientDTO client, WorkerDTO supervisor,
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
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
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

		public void create(ProjectDTO projectDTO) {
			AsyncCallback<ProjectDTO > callback = new AsyncCallback<ProjectDTO >() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
					MessageBox mb = new MessageBox();
					mb.setMessage("Error service.createProject()");
					mb.show();
				}

				public void onSuccess(ProjectDTO result) {
					ProjectPanelView projectPanel = new ProjectPanelView(result);
					projectPanel.setAsMain();
				}
			};
			ProjectServiceAsync service = (ProjectServiceAsync) GWT
					.create(ProjectService.class);
			service.createProject(projectDTO,callback);
		}

}
