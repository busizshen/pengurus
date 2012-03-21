package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.client.panels.center.task.TasksListPanel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class JobPanelProject extends JobPanel {
	ProjectDTO projectDTO;
	public JobPanelProject(JobDTO jobDTO, ProjectDTO projectDTO) {
		super(jobDTO);
		this.projectDTO = projectDTO;
		addTasksList();
	}

	private void addTasksList() {
		TasksListPanel tasks = new TasksListPanel(jobDTO.getTask(), jobDTO, projectDTO);
		add(tasks);
	}
}
