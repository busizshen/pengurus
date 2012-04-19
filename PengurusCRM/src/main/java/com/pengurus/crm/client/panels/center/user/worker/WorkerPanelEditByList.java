package com.pengurus.crm.client.panels.center.user.worker;

import java.util.Set;

import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelEditByList extends WorkerPanelEdit{

	private Set<WorkerDTO> workers;

	public WorkerPanelEditByList(WorkerDTO workerDTO, String heading,Set<WorkerDTO> workers) {
		super(workerDTO, heading);
		this.workers = workers;
		initPanel();
	}

	@Override
	protected void getWorkersPanel() {
		workersListPanel = new WorkersListPanelByList(listenerChangeWorker, listenerCloseTab, workers); 
		
	}

}
