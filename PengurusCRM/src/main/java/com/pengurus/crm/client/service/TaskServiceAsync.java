package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public interface TaskServiceAsync {
	public void createTask(TaskDTO taskDTO,AsyncCallback<TaskDTO> callback);
	public void updateStatus(TaskDTO taskDTO,AsyncCallback<Void> callback);
	public void update(TaskDTO taskDTO,AsyncCallback<Void> callback);
	public void delete(TaskDTO taskDTO,AsyncCallback<Void> callback);

	public void getAllTasks(AsyncCallback<Set<TaskDTO>> callback);
	public void getTasksByExpertId(Long id,AsyncCallback<Set<TaskDTO>> callback);
	
	public void getPaginatedAllTasks(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<TaskModel>> callback);
	public void getPaginatedTasksByExpertId(PagingLoadConfigHelper loadConfig, Long id,
			AsyncCallback<PagingLoadResultHelper<TaskModel>> callback);
}
