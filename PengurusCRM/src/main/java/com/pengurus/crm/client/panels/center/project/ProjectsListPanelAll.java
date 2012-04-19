package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class ProjectsListPanelAll extends ProjectsListPanel {

	public ProjectsListPanelAll() {
		initPagination();
		modelList = new ModelList();
		add(modelList);
	}
	
	@Override
	protected void initPagination() {
		listPagination = new ListPagination<ProjectModel>(new PaginationRpcProxy<ProjectModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<ProjectModel>> callback) {

				ProjectServiceAsync service = (ProjectServiceAsync) GWT
						.create(ProjectService.class);
				service.getPaginatedAllProjects(loadConfig, new PagingCallbackWrapper<ProjectModel>(callback));
			}
		});
	}

}
