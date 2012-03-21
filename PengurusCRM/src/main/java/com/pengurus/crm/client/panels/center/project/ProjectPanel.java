package com.pengurus.crm.client.panels.center.project;

import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelProjectView;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class ProjectPanel extends MainPanel {

	protected ProjectDTO projectDTO;
	
	public ProjectPanel(ProjectDTO projectDTO){
		this.projectDTO = projectDTO;
		setHeading("Project Panel View");
		addInfoPanel();
		addProjectMangaersPanel();
		addJobsPanel();
		addTranslatorsPanel();
		
	}

	protected abstract void addTranslatorsPanel();

	protected void addJobsPanel() {
		JobsListPanelProjectView jobsPanel = new JobsListPanelProjectView(projectDTO);
		add(jobsPanel.getPanel());	
	}

	protected abstract void addProjectMangaersPanel();
	
	protected abstract void addInfoPanel();
}
