package com.pengurus.crm.client.panels.center.job;

import com.pengurus.crm.client.panels.center.task.TasksListPanelEdit;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class JobPanelProject extends JobPanel {
	ProjectDTO projectDTO;
	public JobPanelProject(JobDTO jobDTO, ProjectDTO projectDTO) {
		super(jobDTO);
		this.projectDTO = projectDTO;
		addTasksList();
	}

	private void addTasksList() {
		TasksListPanelEdit tasks = new TasksListPanelEdit(jobDTO.getTask(), jobDTO, projectDTO);
		add(tasks);
	}

	@Override
	protected void setTranslation() {
		if(translation.getTranslation().getTranslationDTO() != jobDTO.getTranslation()){
			jobDTO.setTranslation(translation.getTranslation().getTranslationDTO());
			for(TaskDTO taskDTO : jobDTO.getTask()){
				taskDTO.setTranslation(jobDTO.getTranslation());
			}
		}
	}
}
