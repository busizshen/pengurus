package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelView;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanel;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelInput;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelOutput;
import com.pengurus.crm.client.panels.center.job.JobPanelProject;
import com.pengurus.crm.client.panels.center.rating.CommentPanel;
import com.pengurus.crm.client.panels.center.rating.RatingPanel;
import com.pengurus.crm.client.panels.center.status.TaskStatusPanel;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;

public class TaskPanelView extends TaskPanel {

	TaskStatusPanel statusBar;
	RatingPanel ratingPanel;
	CommentPanel commentPanel;

	public TaskPanelView(TaskDTO taskDTO, ProjectDTO projectDTO) {
		super(taskDTO, projectDTO);
		setHeight(750);
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setSpacing(10);
			
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.add(getDeadlinePanel());
		addDescriptionPanel(topPanel);
		addButtonPanel(topPanel);
		mainVerticalPanel.add(topPanel);
		addStatusBar(mainVerticalPanel);
		
		HorizontalPanel centerPanel = new HorizontalPanel();
		VerticalPanel translationPanel = new VerticalPanel();
		translationPanel.setSpacing(10);
		addTranslationPanel(translationPanel);
		translationPanel.add(getTranslatorPanel());
		translationPanel.add(getReviewerPanel());
		centerPanel.add(translationPanel);
		
		VerticalPanel commentPanel = new VerticalPanel();
		commentPanel.setSpacing(10);
		addCommentPanel(commentPanel);
		// addRatingPanel(commentPanel);
		centerPanel.add(commentPanel);
		mainVerticalPanel.add(centerPanel);
		addFilesPanel(mainVerticalPanel);
		add(mainVerticalPanel);
	}

	private void addCommentPanel(VerticalPanel vp2) {
		commentPanel = new CommentPanel(taskDTO, 100, 300);
		vp2.add(commentPanel);
	}

	protected void addStatusBar(VerticalPanel vp1) {
		statusBar = new TaskStatusPanel(taskDTO);
		vp1.add(statusBar);
	}

	protected void addDescriptionPanel(LayoutContainer container) {
		if (AuthorizationManager.canEditProject(projectDTO)) {
			description = new DescriptionPanelEdit(taskDTO.getDescription(),
					50, 450);
		} else {
			description = new DescriptionPanelView(taskDTO.getDescription(),
					50, 450);
		}
		container.add(description);
	}

	protected void addRatingPanel(VerticalPanel vp2) {
		ratingPanel = new RatingPanel(taskDTO, projectDTO);
		vp2.add(ratingPanel);
	}

	private void getJobPanel() {
		AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage(t.getMessage());
				mb.show();
			}

			@Override
			public void onSuccess(JobDTO result) {
				JobPanelProject jobPanel = new JobPanelProject(result,
						projectDTO);
				jobPanel.setAsMain();
			}
		};
		JobServiceAsync service = (JobServiceAsync) GWT
				.create(JobService.class);
		service.getJob(taskDTO.getJob().getId(), callback);
	}

	protected void addButtonPanel(LayoutContainer container) {
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		hp.setSpacing(5);
		Button b = new Button(myConstants.Update(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				taskDTO.setComment(commentPanel.getComment());
				// taskDTO.setRating();
				taskDTO.setDescription(description.getDescription());
				if (workerPanel.getChosenWorker() != null)
					taskDTO.setExpert((TranslatorDTO) workerPanel
							.getChosenWorker());
				if (reviewerPanel.getChosenWorker() != null)
					taskDTO.setReviewer((TranslatorDTO) reviewerPanel
							.getChosenWorker());
				taskDTO.setStatus(statusBar.getStatus());
				AsyncCallback<Void> callback = new AsyncCallback<Void>() {

					public void onFailure(Throwable t) {
						MessageBox mb = new MessageBox();
						mb.setMessage(myMessages.ServerError(t.getMessage()));
						mb.show();
					}

					@Override
					public void onSuccess(Void result) {
						getJobPanel();
					}
				};
				TaskServiceAsync service = (TaskServiceAsync) GWT
						.create(TaskService.class);
				service.update(taskDTO, callback);

			}
		});
		hp.add(b);
		Button b2 = new Button(myConstants.Cancel(), new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				getJobPanel();
			}
		});
		hp.add(b2);
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Button b3 = new Button(myConstants.Delete(),
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							AsyncCallback<Void> callback = new AsyncCallback<Void>() {

								public void onFailure(Throwable t) {
									MessageBox mb = new MessageBox();
									mb.setMessage(myMessages.ServerError(t.getMessage()));
									mb.show();
								}

								@Override
								public void onSuccess(Void result) {
									getJobPanel();
								}
							};
							TaskServiceAsync service = (TaskServiceAsync) GWT
									.create(TaskService.class);
							service.delete(taskDTO, callback);
						}
					});
			hp.add(b3);
		}
		container.add(hp);
	}

	protected void addTranslationPanel(VerticalPanel simple) {
		if (taskDTO != null && taskDTO.getTranslation() != null)
			translation = new TranslationPanelView(taskDTO.getTranslation(),
					taskDTO.getAmount(), taskDTO.getPrice());
		else
			translation = new TranslationPanelView();
		simple.add(translation);
	}

	protected void addFilesPanel(VerticalPanel vp0) {
		HorizontalPanel filesPanel = new HorizontalPanel();
		FilesPanel filesPanelIn = new FilesPanelInput(projectDTO.getQuoteId(),
				taskDTO.getJob().getId(), taskDTO.getId(), true, true);
		filesPanelIn.setStyleAttribute("margin-right", "30px");
		filesPanel.add(filesPanelIn);
		FilesPanel filesPanelOut = new FilesPanelOutput(
				projectDTO.getQuoteId(), taskDTO.getJob().getId(),
				taskDTO.getId(), true, true);
		filesPanel.add(filesPanelOut);
		vp0.add(filesPanel);
	}

}
