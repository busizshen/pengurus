package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelProjectView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class ProjectPanel extends MainPanel {

	protected WorkerPanelView supervisor;
	protected ProjectDTO projectDTO;
	protected DescriptionPanel descriptionPanel;
	
	public ProjectPanel(ProjectDTO projectDTO){
		this.projectDTO = projectDTO;
		setHeading("Project Panel View");
		addInfoPanel();
		addJobsPanel();
		addProjectMangaersPanel();
		addTranslatorsPanel();
		
	}

	protected abstract void addTranslatorsPanel();

	protected void addJobsPanel() {
		JobsListPanelProjectView jobsPanel = new JobsListPanelProjectView(projectDTO);
		add(jobsPanel.getPanel());	
	}

	protected abstract void addProjectMangaersPanel();
	
	protected void addInfoPanel() {
		FormPanel simple = new FormPanel(); 
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.setSpacing(5);

		addButtonPanel(hp2);

		vp.add(hp2);
		
		NumberField id = new NumberField();
		id.setValue(projectDTO.getId());
		id.setReadOnly(true);
		vp.add(id);
		
		addSupervisorPanel(vp);
		
		addClientPanel(vp);
		
		hp.add(vp);

		addDescriptionPanel(hp);
		
		simple.add(hp);
		add(simple);
	}

	protected abstract void addButtonPanel(HorizontalPanel hp2);

	protected abstract void addDescriptionPanel(HorizontalPanel hp);

	protected abstract void addClientPanel(VerticalPanel vp);

	protected abstract void addSupervisorPanel(VerticalPanel vp);
}
