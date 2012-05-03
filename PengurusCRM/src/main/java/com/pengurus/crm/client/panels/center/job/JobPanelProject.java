package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.project.ProjectPanel;
import com.pengurus.crm.client.panels.center.project.ProjectPanelView;
import com.pengurus.crm.client.panels.center.status.JobStatusPanelProject;
import com.pengurus.crm.client.panels.center.task.TasksListPanelEdit;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
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
		TasksListPanelEdit tasks = new TasksListPanelEdit(jobDTO.getTask(),
				jobDTO, projectDTO);
		add(tasks);
	}

	@Override
	protected void setTranslation() {
		if (translation.getTranslation().getTranslationDTO() != jobDTO
				.getTranslation()) {
			jobDTO.setTranslation(translation.getTranslation()
					.getTranslationDTO());
			for (TaskDTO taskDTO : jobDTO.getTask()) {
				taskDTO.setTranslation(jobDTO.getTranslation());
			}
		}
	}

	@Override
	protected void cancelButton() {
		AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error Cancel");
				mb.show();
			}

			@Override
			public void onSuccess(ProjectDTO result) {
				ProjectPanel projectPanel = new ProjectPanelView(result);
				projectPanel.setAsMain();
			}
		};
		ProjectServiceAsync service = (ProjectServiceAsync) GWT
				.create(ProjectService.class);
		service.getProject(projectDTO.getId(), callback);
	}

	@Override
	protected void addStatusPanel(VerticalPanel vp) {
		this.jobStatusPanel = new JobStatusPanelProject(jobDTO, projectDTO);
		vp.add(jobStatusPanel);
	}

}