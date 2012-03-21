package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskPanelCreate extends TaskPanel {

	private Listener<DomEvent> listenerCreateTask;
	private Listener<DomEvent> listenerClose;
	Button buttonCancel;
	Button buttonCreate;
	private JobDTO jobDTO;

	public TaskPanelCreate(JobDTO jobDTO, ProjectDTO projectDTO) {
		super(new TaskDTO(), projectDTO);
		this.jobDTO = jobDTO;
		taskDTO.setTranslation(jobDTO.getTranslation());
		taskDTO.setJob(jobDTO);
		taskDTO.setStatus(StatusDTO.open);
		if(taskDTO.getTranslation() != null)
			translation.setTranslation(new TranslationModel(taskDTO.getTranslation()));
	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setListeners(Listener<DomEvent> listenerClose,
			Listener<DomEvent> listenerCreateTask) {
		this.listenerClose = listenerClose;
		if(buttonCancel != null)
			buttonCancel.addListener(Events.OnClick, listenerClose);
		this.listenerCreateTask = listenerCreateTask;
		if(buttonCreate != null)
			buttonCreate.addListener(Events.OnClick, listenerCreateTask);
		

	}

	protected void addButtonPanel(VerticalPanel vp1) {
		HorizontalPanel simple = new HorizontalPanel();
		setStyle(simple);
		buttonCreate = new Button("Create", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// create Task DTO
			}
		});
		buttonCreate.addListener(Events.OnClick, listenerCreateTask);
		simple.add(buttonCreate);
		buttonCancel = new Button("Cancel");
		buttonCancel.addListener(Events.OnClick, listenerClose);
		simple.add(buttonCancel);
		add(simple);
		vp1.add(simple);
	}

	protected void addStatusBar(VerticalPanel vp1) {
	}

	protected void addRatingPanel(VerticalPanel vp2) {
	}
	
	
}
