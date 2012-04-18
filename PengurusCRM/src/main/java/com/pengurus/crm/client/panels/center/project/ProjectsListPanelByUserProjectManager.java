package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class ProjectsListPanelByUserProjectManager extends
		ProjectsListPanelByUser {

	public ProjectsListPanelByUserProjectManager(UserDTO user) {
		super(user);
	}

	public ProjectsListPanelByUserProjectManager(UserDTO user, int height,
			int width) {
		super(user, height, width);
	}
	
	@Override
	protected String getName() {
		return user.getFullName() + " projects list as project manager";
	}

	@Override
	protected void initPagination() {
		listPagination = new ListPagination<ProjectModel>(new PaginationRpcProxy<ProjectModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<ProjectModel>> callback) {

				ProjectServiceAsync service = (ProjectServiceAsync) GWT
						.create(ProjectService.class);
				service.getPaginatedProjectsByProjectManagerId(loadConfig, user.getId(), new PagingCallbackWrapper<ProjectModel>(callback));
				
			}
		});
	}

}
