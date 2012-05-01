package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.job.JobPanelProject;
import com.pengurus.crm.client.panels.center.rating.CommentPanel;
import com.pengurus.crm.client.panels.center.rating.RatingPanel;
import com.pengurus.crm.client.panels.center.status.TaskStatusPanel;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
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
		vp.setSpacing(5);
		addButtonPanel(vp);
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(10);
		addStatusBar(vp1);
		vp1.add(getTranslatorPanel());
		vp1.add(getReviewerPanel());
		addInfoPanel(vp1);
		hp.add(vp1);
		VerticalPanel vp2 = new VerticalPanel();
		vp2.setSpacing(10);
		addCommentPanel(vp2);
		/*addRatingPanel(vp2);*/
		addDescriptionPanel(vp2);
		hp.add(vp2);
		vp.add(hp);
		add(vp);

	}

	private void addCommentPanel(VerticalPanel vp2) {
		commentPanel = new CommentPanel(taskDTO);
		vp2.add(commentPanel);
	}

	protected void addStatusBar(VerticalPanel vp1) {
		statusBar = new TaskStatusPanel(taskDTO);
		vp1.add(statusBar);
	}

	protected void addDescriptionPanel(VerticalPanel vp2) {
		description = new DescriptionPanelEdit(taskDTO.getDescription(),100,300);
		vp2.add(description);
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

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel hp = new HorizontalPanel();
		setStyle(hp);
		hp.setSpacing(5);
		Button b = new Button("Update", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (amount.getValue() != null)
					taskDTO.setAmount(amount.getValue().intValue());
				if (price.getValue() != null && combo.getValue() != null)
					taskDTO.setPrice(new PriceDTO(price.getValue().intValue(),
							combo.getValue().getCurrencyDTO()));
				 taskDTO.setComment(commentPanel.getComment());
				// taskDTO.setRating();
				taskDTO.setDescription(description.getDescription());
				if(workerPanel.getChosenWorker() != null)
					taskDTO.setExpert((TranslatorDTO) workerPanel.getChosenWorker());
				if(reviewerPanel.getChosenWorker() != null)
					taskDTO.setReviewer((TranslatorDTO) reviewerPanel.getChosenWorker());
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
		vp1.add(hp);
	}

}
