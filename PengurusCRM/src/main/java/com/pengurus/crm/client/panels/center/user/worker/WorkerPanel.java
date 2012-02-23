package com.pengurus.crm.client.panels.center.user.worker;

import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanel extends UserPanel {

	WorkerDTO workerDTO;
	public WorkerPanel(WorkerDTO workerDTO) {
		this.workerDTO = workerDTO; 
	}

	public Widget getInfoPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUserHeading() {
		return "Worker";
	}

	@Override
	protected String getUsername() {
		return workerDTO.getUsername();
	}

	@Override
	protected String getUserDescription() {
		return workerDTO.getDescription();
	}

	@Override
	protected void addEditionPanel(UserViewInfo userViewInfo) {
		// TODO Auto-generated method stub
		
	}

}
