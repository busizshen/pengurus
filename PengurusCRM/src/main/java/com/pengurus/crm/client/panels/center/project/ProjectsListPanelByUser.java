package com.pengurus.crm.client.panels.center.project;

import com.pengurus.crm.shared.dto.UserDTO;

public abstract class ProjectsListPanelByUser extends ProjectsListPanel {

	UserDTO user;

	public ProjectsListPanelByUser(UserDTO user) {
		initPagination();
		this.user = user;
		modelList = new ModelList();
		add(modelList);
	}

	public ProjectsListPanelByUser(UserDTO user, int height, int width) {
		initPagination();
		this.user = user;
		modelList = new ModelList(height, width);
		add(modelList);
	}
}
