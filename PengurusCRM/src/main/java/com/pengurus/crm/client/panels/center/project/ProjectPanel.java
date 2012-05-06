package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelProjectView;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class ProjectPanel extends MainPanel {

	protected WorkerPanelView supervisorPanel;
	protected ProjectDTO projectDTO;
	protected DescriptionPanel descriptionPanel;
	private ClientPanelView clientPanel;
	
	public ProjectPanel(ProjectDTO projectDTO){
		super(800);
		this.projectDTO = projectDTO;
		setHeading("Project");
		addInfoPanel();
	}

	protected abstract void getTranslatorsPanel(HorizontalPanel hp3);

	protected void addJobsPanel(VerticalPanel vp) {
		JobsListPanelProjectView jobsPanel = new JobsListPanelProjectView(projectDTO);
		vp.add(jobsPanel);	
	}

	protected abstract void getProjectManagersPanel(HorizontalPanel hp3);
	
	protected void addInfoPanel() {
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		mainVerticalPanel.setSpacing(10);
		
		HorizontalPanel topHorizontalPanel = new HorizontalPanel();
		addDescriptionPanel(topHorizontalPanel);
		addButtonPanel(topHorizontalPanel);
		
		mainVerticalPanel.add(topHorizontalPanel);
		
		VerticalPanel personalVerticalPanel = new VerticalPanel();
		addSupervisorPanel(personalVerticalPanel);
		addClientPanel(personalVerticalPanel);
		mainVerticalPanel.add(personalVerticalPanel);
		
		HorizontalPanel expertsHorizontalPanel = new HorizontalPanel();
		
		getProjectManagersPanel(expertsHorizontalPanel);
		getTranslatorsPanel(expertsHorizontalPanel);
		
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainVerticalPanel.add(expertsHorizontalPanel);
		
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		addJobsPanel(mainVerticalPanel);
		
		add(mainVerticalPanel);

	}

	protected abstract void addButtonPanel(HorizontalPanel hp2);

	protected abstract void addDescriptionPanel(HorizontalPanel vp);

	protected void addSupervisorPanel(VerticalPanel vp) {
		supervisorPanel = new WorkerPanelView(projectDTO.getSupervisor(),"Supervisor");
		supervisorPanel.setHeading("Supervisor");
		vp.add(supervisorPanel);
	}


	protected void addClientPanel(VerticalPanel vp) {
		if(AuthorizationManager.canViewWholeProject()){
			clientPanel = new ClientPanelView(projectDTO.getClient(),"Client");
			clientPanel.setHeading("Client");
			vp.add(clientPanel);
		}
	}
}
