package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

@RemoteServiceRelativePath("task.rpc")
public interface TaskService extends RemoteService {
	public TaskDTO createTask(TaskDTO taskDTO);
	public void updateStatus(TaskDTO taskDTO);
	public void update(TaskDTO taskDTO);
	public void delete(TaskDTO taskDTO);

	public Set<TaskDTO> getAllTasks();
	public Set<TaskDTO> getTasksByExpertId(Long id);

	public PagingLoadResultHelper<TaskModel> getPaginatedAllTasks(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<TaskModel> getPaginatedTasksByExpertId(PagingLoadConfigHelper loadConfig, Long id);
}
