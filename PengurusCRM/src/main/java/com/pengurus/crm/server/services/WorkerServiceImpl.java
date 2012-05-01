package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pengurus.crm.client.service.WorkerService;
import com.pengurus.crm.daos.WorkerDAO;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerServiceImpl implements WorkerService {

	private WorkerDAO workerDAO;
	
	@Override
	public Set<WorkerDTO> getWorkersByRoles(Set<UserRoleDTO> roles) {
		List<Worker> list = workerDAO.loadAll();
		Set<WorkerDTO> set = new HashSet<WorkerDTO>();
		for (Worker u : list) {
			for (UserRoleDTO role : roles) {
				if (u.getAuthorities().contains(role)){
					set.add(u.toDTO());
					break;
				}
			}
		}
		return set;
	}

	public void setWorkerDAO(WorkerDAO workerDAO) {
		this.workerDAO = workerDAO;
	}

	public WorkerDAO getWorkerDAO() {
		return workerDAO;
	}

}
