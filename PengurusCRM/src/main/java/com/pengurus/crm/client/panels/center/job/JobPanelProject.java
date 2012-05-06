package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelView;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanel;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelInput;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelOutput;
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
		super(jobDTO, 900);
		this.projectDTO = projectDTO;
		addInfoPanel();
	}

	@Override
	protected void addInfoPanel() {
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		HorizontalPanel topHorizontalPanel = new HorizontalPanel();
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainVerticalPanel.setSpacing(15);
		
		topHorizontalPanel.add(getDeadlinePanel());
		deadline.setReadOnly(true);
		topHorizontalPanel.add(addDescriptionPanel());
		topHorizontalPanel.add(getbuttonPanel());
		
		mainVerticalPanel.add(topHorizontalPanel);
		
		
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addStatusPanel(mainVerticalPanel);
		
		
		addTranslationPanel(mainVerticalPanel);
		
		
		addTasksList(mainVerticalPanel);
		
		HorizontalPanel filesPanel = new HorizontalPanel();
		addFilesPanel(filesPanel);
		mainVerticalPanel.add(filesPanel);
		
		add(mainVerticalPanel);
	}

	private void addTasksList(VerticalPanel vp) {
		TasksListPanelEdit tasks = new TasksListPanelEdit(jobDTO.getTask(),
				jobDTO, projectDTO);
		vp.add(tasks);
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

	@Override
	protected void addTranslationPanel(VerticalPanel vp) {
		if (jobDTO.getTranslation() != null)
			translation = new TranslationPanelView(
					jobDTO.getTranslation(), jobDTO.getAmount(),
					jobDTO.getPrice());
		else
			translation = new TranslationPanelView();
		
		translation.setWidth(200);
		vp.add(translation);
	}

	protected DescriptionPanel addDescriptionPanel() {
		description = new DescriptionPanelView(jobDTO.getDescription(), 50, 400);
		description.setStyleAttribute("margin-right", "30px");
		return description;
	}

	@Override
	protected void addFilesPanel(HorizontalPanel hp0) {
		FilesPanel filesPanelIn = new FilesPanelInput(projectDTO.getQuoteId(),
				jobDTO.getId(), new Long(0), true, true);
		hp0.add(filesPanelIn);
		filesPanelIn.setStyleAttribute("margin-right", "40px");
		FilesPanel filesPanelOut = new FilesPanelOutput(
				projectDTO.getQuoteId(), jobDTO.getId(), new Long(0), true,
				true);
		hp0.add(filesPanelOut);
	}

}