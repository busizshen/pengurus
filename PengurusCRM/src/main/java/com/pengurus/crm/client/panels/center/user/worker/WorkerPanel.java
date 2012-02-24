package com.pengurus.crm.client.panels.center.user.worker;

import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class WorkerPanel extends UserPanel {

	WorkerDTO workerDTO;
	public WorkerPanel(WorkerDTO workerDTO) {
		super(workerDTO);
		this.workerDTO = workerDTO; 
		userInfoPanel = new UserViewInfo();
	}

	public WorkerPanel() {
		super(null);
		userInfoPanel = new UserViewInfo();
	}

	@Override
	protected String getUserHeading() {
		return "Worker";
	}

	@Override
	protected String getUserDescription() {
		if(workerDTO != null)
			return workerDTO.getDescription();
		return null;
	}

	public abstract WorkerDTO getChosenWorker();

}
