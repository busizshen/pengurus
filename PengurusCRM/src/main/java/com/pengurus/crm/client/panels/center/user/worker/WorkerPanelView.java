package com.pengurus.crm.client.panels.center.user.worker;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelView extends WorkerPanel{

	public WorkerPanelView(WorkerDTO workerDTO,String heading) {
		super(workerDTO, heading);
		initPanel();
		refresh(workerDTO);
	}
	public WorkerDTO getChosenWorker() {
		return (WorkerDTO) userDTO;
	}
	@Override
	protected void addEditionPanel(HorizontalPanel hp) {
		// TODO Auto-generated method stub
		
	}
}
