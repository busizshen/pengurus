package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskPanelCreate extends TaskPanel {

	private Listener<DomEvent> listenerCreateTask;
	private Listener<DomEvent> listenerClose;
	private JobDTO jobDTO;
	Button buttonCancel;
	Button buttonCreate;
	TasksListPanelEdit taskList;

	public TaskPanelCreate(JobDTO jobDTO, ProjectDTO projectDTO, TasksListPanelEdit taskListPanel) {
		super(null, projectDTO);
		setFrame(false);
		setHeaderVisible(false);
		this.jobDTO = jobDTO;
		if (jobDTO.getTranslation() != null)
			translation.setTranslation(new TranslationModel(jobDTO
					.getTranslation()));
		combo.setAllowBlank(false);
		amount.setAllowBlank(false);
		price.setAllowBlank(false);
		description.setAllowBlank(false);
		deadline.setAllowBlank(false);
		taskList = taskListPanel;

	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setListeners(Listener<DomEvent> listenerClose,
			Listener<DomEvent> listenerCreateTask) {
		this.listenerClose = listenerClose;
		if (buttonCancel != null)
			buttonCancel.addListener(Events.OnClick, listenerClose);
		this.listenerCreateTask = listenerCreateTask;
		if (buttonCreate != null)
			buttonCreate.addListener(Events.OnClick, listenerCreateTask);

	}

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel simple = new HorizontalPanel();
		setStyle(simple);
		buttonCreate = new Button("Create",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						if (checked()) {
							taskDTO = new TaskDTO();
							taskDTO.setTranslation(jobDTO.getTranslation());
							taskDTO.setJob(jobDTO);
							taskDTO.setStatus(StatusDTO.open);
							taskDTO.setAmount(amount.getValue().intValue());
							taskDTO.setPrice(new PriceDTO(price.getValue()
									.intValue(), combo.getValue()
									.getCurrencyDTO()));
							taskDTO.setDeadline(deadline.getValue());
							taskDTO.setDescription(description.getDescription());
							AsyncCallback<TaskDTO> callback = new AsyncCallback<TaskDTO>() {

								public void onFailure(Throwable t) {
									Window.Location
											.assign("/spring_security_login");

								}

								@Override
								public void onSuccess(TaskDTO result) {
									jobDTO.getTask().add(result);
									taskList.refreshList(result);
								}
							};
							TaskServiceAsync service = (TaskServiceAsync) GWT
									.create(TaskService.class);
							service.createTask(taskDTO, callback);
						} else {
							MessageBox mb = new MessageBox();
							mb.setMessage("Please fill all fields");
							mb.show();
						}
					}

				});
		buttonCreate.addListener(Events.OnClick, listenerCreateTask);
		mainPanel.addButton(buttonCreate);
		buttonCancel = new Button("Cancel");
		buttonCancel.addListener(Events.OnClick, listenerClose);
		mainPanel.addButton(buttonCancel);

		mainPanel.setButtonAlign(HorizontalAlignment.CENTER);
	}

	protected boolean checked() {
		if (amount.getValue() == null)
			return false;
		if (price.getValue() == null)
			return false;
		if (combo.getValue() == null)
			return false;
		if (description.getDescription() == null)
			return false;
		if(deadline.getValue() == null) return false;
		return true;
	}

	protected void addStatusBar(VerticalPanel vp1) {
	}

	protected void addRatingPanel(VerticalPanel vp2) {
	}
	
	protected void addDescriptionPanel(VerticalPanel vp2) {
		description = new DescriptionPanelEdit("");
		description.setWidth(300);
		vp2.add(description);
	}

}
