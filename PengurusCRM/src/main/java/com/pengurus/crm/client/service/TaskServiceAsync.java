package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.TaskDTO;

public interface TaskServiceAsync {
	public void createTask(TaskDTO taskDTO,AsyncCallback<TaskDTO> callback);
	public void updateStatus(TaskDTO taskDTO,AsyncCallback<Void> callback);
	public void update(TaskDTO taskDTO,AsyncCallback<Void> callback);
	public void delete(TaskDTO taskDTO,AsyncCallback<Void> callback);
	public void getTaskByExpertId(Long id,AsyncCallback<Void> callback);
	public void getTasks(AsyncCallback<Set<TaskDTO>> callback);
}
