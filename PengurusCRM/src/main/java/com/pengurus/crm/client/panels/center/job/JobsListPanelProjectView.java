package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.shared.dto.ProjectDTO;

public class JobsListPanelProjectView extends JobsListPanelProject{

	public JobsListPanelProjectView(ProjectDTO projectDTO) {
		super(projectDTO, 275);
		setHeading("Jobs");
		modelList = new ModelList(225, 825);
		add(modelList);
	}

}
