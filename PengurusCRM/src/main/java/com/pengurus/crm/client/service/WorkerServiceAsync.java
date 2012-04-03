package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public interface WorkerServiceAsync {
	public void getWorkersByRoles(Set<UserRoleDTO> roles, AsyncCallback<Set<WorkerDTO>> callback);

}
