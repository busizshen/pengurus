package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class JobsListPanelProjectView extends JobsListPanelProject{

	public JobsListPanelProjectView(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	public ContentPanel getPanel() {
		setHeading("Jobs");
		setCollapsible(true);
		setAnimCollapse(true);
		collapse();
		modelList = new ModelList();
		add(modelList);
		return this;
	}

}
