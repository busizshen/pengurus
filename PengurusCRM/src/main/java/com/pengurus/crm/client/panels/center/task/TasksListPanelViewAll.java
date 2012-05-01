package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.PaginationService;
import com.pengurus.crm.client.service.PaginationServiceAsync;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;



public class TasksListPanelViewAll extends TasksListPanelView {

	public TasksListPanelViewAll() {
		initPanel();
	}

	@Override
	protected void initPaging() {
		listPagination = new ListPagination<TaskModel>(new PaginationRpcProxy<TaskModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<TaskModel>> callback) {
				PaginationServiceAsync service = (PaginationServiceAsync) GWT
						.create(PaginationService.class);
				service.getPaginatedAllTasks(loadConfig, new PagingCallbackWrapper<TaskModel>(callback));
			}
		});
	}

}
