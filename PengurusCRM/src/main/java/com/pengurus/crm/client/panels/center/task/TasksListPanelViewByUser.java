package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class TasksListPanelViewByUser extends TasksListPanelView {

	UserDTO user;
	
	public TasksListPanelViewByUser(UserDTO user) {
		this.user = user;
		initPanel();
	}
	
	public TasksListPanelViewByUser(UserDTO user, int height, int width) {
		this.user = user;
		initPanel(height, width);
	}

	@Override
	protected String getName() {
		return user.getFullName() + " tasks list";
	}

	@Override
	protected void initPaging() {
		listPagination = new ListPagination<TaskModel>(new PaginationRpcProxy<TaskModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<TaskModel>> callback) {
				TaskServiceAsync service = (TaskServiceAsync) GWT
						.create(TaskService.class);
				service.getPaginatedTasksByExpertId(loadConfig, user.getId(), new PagingCallbackWrapper<TaskModel>(callback));
			}
		});
	}
	
}
