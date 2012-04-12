package com.pengurus.crm.client.panels.center.user.worker;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelEdit extends WorkerPanel {

	WorkersListPanel cl;
	public WorkerPanelEdit(WorkerDTO workerDTO) {
		super(workerDTO);
		addEditionPanel();
	}

	public WorkerPanelEdit() {
		super();
		addEditionPanel();
	}

	private void addEditionPanel() {
		final Window window = new Window();
		Listener<DomEvent> listenerCloseTab = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
				window.hide();
			}};
		Listener<DomEvent> listenerChangeWorker = new Listener<DomEvent>() {
				@Override
				public void handleEvent(DomEvent be) {
					workerDTO = getChosenWorker();
					refresh(workerDTO);
				}
		};
		cl = new WorkersListPanel(listenerChangeWorker, listenerCloseTab);  
		window.add(cl.getPanel());
		window.setAutoWidth(true);
		window.setAutoHide(true);
		window.setHeading("Change Supervisor");
		window.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		window.add(b2);
		Button b = new Button("Change Supervisor", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {		
				window.show();
				
			}  });
		add(b);
		
	}

	@Override
	public WorkerDTO getChosenWorker() {
		return cl.getChosenWorker();
	}


}
