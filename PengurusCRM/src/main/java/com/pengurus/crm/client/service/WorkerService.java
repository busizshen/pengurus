package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

@RemoteServiceRelativePath("worker.rpc")
public interface WorkerService  extends RemoteService{
	public Set<WorkerDTO> getWorkersByRoles(Set<UserRoleDTO> roles);
}
