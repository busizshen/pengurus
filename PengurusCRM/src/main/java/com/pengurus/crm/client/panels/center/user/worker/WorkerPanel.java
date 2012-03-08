package com.pengurus.crm.client.panels.center.user.worker;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Widget;
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

	public Widget getInfoForm() {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		
		TextField<String> login = new TextField<String>();
		login.setFieldLabel("Login");
		login.setReadOnly(true);
		login.setValue(workerDTO.getUsername());
		simple.add(login);
		
		if(workerDTO.getPersonalData() != null){
			TextField<String> name = new TextField<String>();
			name.setFieldLabel("Login");
			name.setReadOnly(true);
			name.setValue(workerDTO.getPersonalData().getFullName());
			simple.add(name);
		}
		
		return simple;
	}
}
