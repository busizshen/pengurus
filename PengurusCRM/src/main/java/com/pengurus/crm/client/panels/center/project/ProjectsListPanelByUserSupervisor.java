package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.PaginationService;
import com.pengurus.crm.client.service.PaginationServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class ProjectsListPanelByUserSupervisor extends ProjectsListPanelByUser {

	public ProjectsListPanelByUserSupervisor(UserDTO user) {
		super(user);
	}

	public ProjectsListPanelByUserSupervisor(UserDTO user, int height, int width) {
		super(user, height, width);
	}

	@Override
	protected void initPagination() {
		listPagination = new ListPagination<ProjectModel>(new PaginationRpcProxy<ProjectModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<ProjectModel>> callback) {

				PaginationServiceAsync service = (PaginationServiceAsync) GWT
						.create(PaginationService.class);
				service.getPaginatedProjectsBySupervisorId(loadConfig, user.getId(), new PagingCallbackWrapper<ProjectModel>(callback));
			}
		});
	}
	
	@Override
	protected String getName() {
		return  myMessages.ProjectsList(user.getFullName(),myConstants.Supervisor());
	}

}
