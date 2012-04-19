package com.pengurus.crm.client.panels.center.user.worker;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class WorkerPanelEdit extends WorkerPanel {

	WorkersListPanel workersListPanel;
	Listener<DomEvent> listenerChangeWorker;
	Listener<DomEvent> listenerCloseTab;
	public WorkerPanelEdit(WorkerDTO workerDTO, String heading) {
		super(workerDTO, heading);
	}
	
	public WorkerPanelEdit() {
		super();
	}

	protected void addEditionPanel(HorizontalPanel hp) {
		final Window window = new Window();
		listenerCloseTab = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
				window.hide();
			}};
		listenerChangeWorker = new Listener<DomEvent>() {
				@Override
				public void handleEvent(DomEvent be) {
					userDTO = getChosenWorker();
					refresh(userDTO);
				}
		};
		getWorkersPanel();
		window.add(workersListPanel.getModelList());
		window.setAutoWidth(true);
		window.setAutoHide(true);
		window.setHeading("Select");
		window.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		window.add(b2);
		Button b = new Button("Change", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {		
				window.show();
				
			}  });
		hp.add(b);
		
	}
	protected abstract void getWorkersPanel();

	@Override
	public WorkerDTO getChosenWorker() {
		return workersListPanel.getChosenWorker();
	}

}
