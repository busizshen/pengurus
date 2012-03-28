package com.pengurus.crm.server;

import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskServiceImpl implements TaskService {

	private TaskDAO taskDAO;
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	@Override
	public TaskDTO createTask(TaskDTO taskDTO) {
		taskDTO.setId(taskDAO.create(new Task(taskDTO)));
		return taskDTO;
	}

}
