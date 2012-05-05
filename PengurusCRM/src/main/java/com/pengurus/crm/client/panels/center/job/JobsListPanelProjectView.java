package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class JobsListPanelProjectView extends JobsListPanelProject{

	public JobsListPanelProjectView(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	public MainPanel getPanel() {
		setHeading("Jobs");
		modelList = new ModelList();
		add(modelList);
		return this;
	}

}
