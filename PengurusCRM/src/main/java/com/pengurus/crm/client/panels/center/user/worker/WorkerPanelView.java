package com.pengurus.crm.client.panels.center.user.worker;

import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelView extends WorkerPanel{

	public WorkerPanelView(WorkerDTO workerDTO,String heading) {
		super(workerDTO, heading);
	}
	public WorkerDTO getChosenWorker() {
		return workerDTO;
	}
}
