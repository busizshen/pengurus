package com.pengurus.crm.client.panels.center.user.worker;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.center.user.UserPanel;
import com.pengurus.crm.shared.dto.WorkerDTO;

public abstract class WorkerPanel extends UserPanel {

	WorkerDTO workerDTO;
	TextField<String> login;
	TextField<String> name;
	public WorkerPanel(WorkerDTO workerDTO, String heading) {
		super(workerDTO, heading);
		refresh(workerDTO);
	}

	public WorkerPanel() {
		super(null, "");
	}


	public abstract WorkerDTO getChosenWorker();

	public Widget getInfoForm() {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);

		login = new TextField<String>();
		login.setFieldLabel("Login");
		login.setReadOnly(true);
		simple.add(login);

		name = new TextField<String>();
		name.setFieldLabel("Full Name");
		name.setReadOnly(true);
		simple.add(name);

		return simple;
	}

	public void refresh(WorkerDTO workerDTO) {
		this.workerDTO = workerDTO;
		super.userDTO = workerDTO;
		if (workerDTO != null) {
			login.setValue(workerDTO.getUsername());
			name.setValue(workerDTO.getFullName());
			descriptionPanel.setDescription(workerDTO.getDescription());
		}

	}
}
