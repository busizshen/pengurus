package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;
import com.pengurus.crm.enums.StatusTask;
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
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_PROJECTMANAGER')")
	public TaskDTO createTask(TaskDTO taskDTO) {
		taskDTO.setId(taskDAO.create(new Task(taskDTO)));
		return taskDTO;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_PROJECTMANAGER') or hasPermission(#taskDTO, 'write')")
	public void updateStatus(TaskDTO taskDTO) {
		Task task = taskDAO.read(taskDTO.getId());
		task.setStatus(StatusTask.toStatus(taskDTO.getStatus()));
		taskDAO.update(task);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_PROJECTMANAGER') or hasPermission(#taskDTO, 'write')")
	public void update(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		taskDAO.update(task);
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_EXPERT')")
	public void delete(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		taskDAO.delete(task);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_WORKER')")
	@PostFilter("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_PROJECTMANAGER', 'ROLE_ACCOUNTANT') or hasPermission(filterObject, 'read')")
	public Set<TaskDTO> getTasksByExpertId(Long id) {
		List<Task> list = taskDAO.loadAllByExpertId(id);
		Set<TaskDTO> set = new HashSet<TaskDTO>();
		for (Task q : list) {
			set.add(q.toDTO());
		}
		return set;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_WORKER')")
	@PostFilter("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_PROJECTMANAGER', 'ROLE_ACCOUNTANT') or hasPermission(filterObject, 'read')")
	public Set<TaskDTO> getAllTasks() {
		List<Task> list = taskDAO.loadAll();
		Set<TaskDTO> set = new HashSet<TaskDTO>();
		for (Task task : list) {
			set.add(task.toDTO());
		}
		return set;
	}
}
