package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelProjectView;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelChoose;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class ProjectPanel extends MainPanel {

	protected WorkerPanelView supervisorPanel;
	protected ProjectDTO projectDTO;
	protected DescriptionPanel descriptionPanel;
	private ClientPanelView clientPanel;
	
	public ProjectPanel(ProjectDTO projectDTO){
		this.projectDTO = projectDTO;
		setHeading("Project Panel View");
		addInfoPanel();
		addJobsPanel();
		
	}

	protected abstract WorkerPanelChoose getTranslatorsPanel();

	protected void addJobsPanel() {
		JobsListPanelProjectView jobsPanel = new JobsListPanelProjectView(projectDTO);
		add(jobsPanel.getPanel());	
	}

	protected abstract WorkerPanelChoose getProjectMangaersPanel();
	
	protected void addInfoPanel() {
		FormPanel simple = new FormPanel(); 
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);
		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.setSpacing(5);

		addButtonPanel(hp2);

		simple.add(hp2);
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		addSupervisorPanel(vp);
		
		addClientPanel(vp);
		
		hp.add(vp);

		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(20);
		addDescriptionPanel(vp2);
		
		hp.add(vp2);
		simple.add(hp);
		HorizontalPanel hp3 = new HorizontalPanel();
		hp3.setSpacing(20);
		hp3.add(getProjectMangaersPanel());
		hp3.add(getTranslatorsPanel());
		simple.add(hp3);
		add(simple);
	}

	protected abstract void addButtonPanel(HorizontalPanel hp2);

	protected abstract void addDescriptionPanel(VerticalPanel vp);

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
