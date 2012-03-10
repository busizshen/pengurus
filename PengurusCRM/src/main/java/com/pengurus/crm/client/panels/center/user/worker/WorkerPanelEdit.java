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
	private Listener<DomEvent> listenerChangeWorker;
	public WorkerPanelEdit(WorkerDTO workerDTO, Listener<DomEvent> listener) {
		super(workerDTO);
		this.listenerChangeWorker = listener;
		addEditionPanel(userInfoPanel);
	}

	public WorkerPanelEdit(Listener<DomEvent> listener) {
		super();
		this.listenerChangeWorker = listener;
		addEditionPanel(userInfoPanel);
	}

	private void addEditionPanel(UserViewInfo userInfoPanel) {
		final Window w = new Window();
		Listener<DomEvent> listenerCloseTab = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
				w.hide();
			}};
		cl = new WorkersListPanel(this.listenerChangeWorker, listenerCloseTab);  
		w.add(cl.getPanel());
		w.setAutoWidth(true);
		w.setAutoHide(true);
		w.setHeading("Change Supervisor");
		w.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		w.add(b2);
		Button b = new Button("Change Supervisor", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {		
				w.show();
				
			}  });
		userInfoPanel.add(b);
		
	}

	@Override
	public WorkerDTO getChosenWorker() {
		return cl.getChosenWorker();
	}


}
