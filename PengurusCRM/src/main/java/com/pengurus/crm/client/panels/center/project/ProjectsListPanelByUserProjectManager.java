package com.pengurus.crm.client.panels.center.project;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public class ProjectsListPanelByUserProjectManager extends
		ProjectsListPanelByUser {

	public ProjectsListPanelByUserProjectManager(UserDTO user) {
		super(user);
	}

	@Override
	protected void changeService(AsyncCallback<Set<ProjectDTO>> callback) {

		ProjectServiceAsync service = (ProjectServiceAsync) GWT
				.create(ProjectService.class);
		service.getProjectByProjectManagerId(user.getId(), callback);

	}

}
