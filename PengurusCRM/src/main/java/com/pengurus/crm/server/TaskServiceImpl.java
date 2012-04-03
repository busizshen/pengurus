package com.pengurus.crm.server;

import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.enums.Status;
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
	@Override
	public void updateStatus(TaskDTO taskDTO) {
		Task task = taskDAO.read(taskDTO.getId());
		task.setStatus(Status.toStatus(taskDTO.getStatus()));
		taskDAO.update(task);
	}
	
	@Override
	public void update(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		taskDAO.update(task);
	}

	@Override
	public void delete(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		taskDAO.delete(task);
	}
}
