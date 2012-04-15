package com.pengurus.crm.client.panels.center.user.worker;

import java.util.Set;

import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelEditByRoles extends WorkerPanelEdit {

	private Set<UserRoleDTO> roles;

	public WorkerPanelEditByRoles(WorkerDTO workerDTO, String heading, Set<UserRoleDTO> roles) {
		super(workerDTO, heading);
		this.roles =roles;
		addEditionPanel();
	}

	public WorkerPanelEditByRoles() {
		super();
	}

	@Override
	protected void getWorkersPanel() {
		workersListPanel = new WorkersListPanelByRoles(listenerChangeWorker, listenerCloseTab, roles);  
	}


}
