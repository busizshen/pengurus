package com.pengurus.crm.client.panels.center.user.worker;

import java.util.Set;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.models.WorkerModel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkersListPanelByList extends WorkersListPanel {

	private Set<WorkerDTO> workers;

	public WorkersListPanelByList(Listener<DomEvent> listenerChangeWorker,
			Listener<DomEvent> listenerCloseTab,Set<WorkerDTO> workers) {
		this.listenerChangeWorker = listenerChangeWorker;
		this.listenerCloseTab = listenerCloseTab;
		this.workers = workers;
	}
	
	@Override
	protected ListStore<WorkerModel> getList() {
		ListStore<WorkerModel> listStore = new ListStore<WorkerModel>();
		for (WorkerDTO worker : workers) {
			listStore.add(new WorkerModel(worker));
		}
		return listStore;
	}

}
