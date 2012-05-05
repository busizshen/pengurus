package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
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
		this.projectDTO = projectDTO;
		setHeading("Project");
		addInfoPanel();
		
	}

	protected abstract void getTranslatorsPanel(HorizontalPanel hp3);

	protected void addJobsPanel(VerticalPanel vp) {
		JobsListPanelProjectView jobsPanel = new JobsListPanelProjectView(projectDTO);
		vp.add(jobsPanel.getPanel());	
	}

	protected abstract void getProjectMangaersPanel(HorizontalPanel hp3);
	
	protected void addInfoPanel() {
		VerticalPanel vp0 = new VerticalPanel();
		HorizontalPanel hp0 = new HorizontalPanel();
		addDescriptionPanel(hp0);
		addButtonPanel(hp0);
		vp0.add(hp0);
		VerticalPanel vp = new VerticalPanel();
		addSupervisorPanel(vp);
		addClientPanel(vp);
		vp0.add(vp);
		HorizontalPanel hp3 = new HorizontalPanel();
		hp3.setSpacing(20);
		getProjectMangaersPanel(hp3);
		getTranslatorsPanel(hp3);
		vp0.add(hp3);
		addJobsPanel(vp0);
		
		add(vp0);

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
