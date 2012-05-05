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
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(10);
		final HorizontalPanel hp1 = new HorizontalPanel();
		hp1.add(getDeadlinePanel());
		addDescriptionPanel(hp1);
		addButtonPanel(hp1);
		vp.add(hp1);
		addStatusBar(vp);
		HorizontalPanel hp2 = new HorizontalPanel();
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(10);
		addTranslationPanel(vp1);
		vp1.add(getTranslatorPanel());
		vp1.add(getReviewerPanel());
		hp2.add(vp1);
		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(10);
		addCommentPanel(vp2);
		// addRatingPanel(vp2);
		hp2.add(vp2);
		vp.add(hp2);
		addFilesPanel(vp);
		add(vp);
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
		description = new DescriptionPanelEdit(taskDTO.getDescription(), 50,
				450);
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
		Button b = new Button("Update", new SelectionListener<ButtonEvent>() {
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
						mb.setMessage("Server error - Task cannot be updated");
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
		Button b2 = new Button("Cancel", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				getJobPanel();
			}
		});
		hp.add(b2);
		if (AuthorizationManager.canEditProject(projectDTO)) {
			Button b3 = new Button("Delete",
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							AsyncCallback<Void> callback = new AsyncCallback<Void>() {

								public void onFailure(Throwable t) {
									MessageBox mb = new MessageBox();
									mb.setMessage("Server Error - cannot delete");
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
			translation = new TranslationPanelView(
					taskDTO.getTranslation(), taskDTO.getAmount(),
					taskDTO.getPrice());
		else
			translation = new TranslationPanelView();
		simple.add(translation);
	}

	protected void addFilesPanel(VerticalPanel vp0) {
		FilesPanel filesPanelIn = new FilesPanelInput(projectDTO.getQuoteId(),
				taskDTO.getJob().getId(), taskDTO.getId(), true, true);
		vp0.add(filesPanelIn);
		FilesPanel filesPanelOut = new FilesPanelOutput(
				projectDTO.getQuoteId(), taskDTO.getJob().getId(),
				taskDTO.getId(), true, true);
		vp0.add(filesPanelOut);
	}

}
