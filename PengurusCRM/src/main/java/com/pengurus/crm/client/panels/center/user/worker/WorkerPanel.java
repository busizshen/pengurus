package com.pengurus.crm.client.panels.center.user.worker;

import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class WorkerPanel extends UserPanel {

	public WorkerPanel(WorkerDTO workerDTO, String heading) {
		super(workerDTO, heading);
	}

	public WorkerPanel() {
		super(null, "");
	}


	public abstract WorkerDTO getChosenWorker();

}
