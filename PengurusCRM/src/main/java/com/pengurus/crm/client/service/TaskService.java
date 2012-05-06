package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.TaskDTO;

@RemoteServiceRelativePath("task.rpc")
public interface TaskService extends RemoteService {
	
	public TaskDTO createTask(TaskDTO taskDTO);
	public void updateStatus(TaskDTO taskDTO);
	public void update(TaskDTO taskDTO);
	public void delete(TaskDTO taskDTO);

	public Set<TaskDTO> getAllTasks();
	public Set<TaskDTO> getTasksByExpertId(Long id);
	public Set<TaskDTO> getTasksByReviewerId(Long id);
}
