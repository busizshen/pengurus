package com.pengurus.crm.client.panels.center.status;

import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class JobStatusPanelProject extends JobStatusPanel {

	ProjectDTO projectDTO;

	public JobStatusPanelProject(JobDTO jobDTO, ProjectDTO projectDTO) {
		super(jobDTO);
		this.projectDTO = projectDTO;
		initPanel();

	}

	@Override
	protected void setVisibility() {
		nextStatus.setVisible((status < 2)
				&& (AuthorizationManager.canEditProject(projectDTO))
				|| AuthorizationManager.hasExecutiveAccess());

		reOpen.setVisible((AuthorizationManager.hasExecutiveAccess() && status == 2));

		this.layout();

	}

}
