package com.pengurus.crm.client.panels.center.project;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.panels.center.ListPanel.ModelList;
import com.pengurus.crm.client.service.PaginationService;
import com.pengurus.crm.client.service.PaginationServiceAsync;
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

				PaginationServiceAsync service = (PaginationServiceAsync) GWT
						.create(PaginationService.class);
				service.getPaginatedAllProjects(loadConfig, new PagingCallbackWrapper<ProjectModel>(callback));
			}
		});
	}

}
