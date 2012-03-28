package com.pengurus.crm.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.TaskDTO;

public interface TaskServiceAsync {
	public void createTask(TaskDTO taskDTO,AsyncCallback<TaskDTO> callback);
}
