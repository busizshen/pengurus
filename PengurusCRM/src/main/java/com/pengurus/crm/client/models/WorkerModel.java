package com.pengurus.crm.client.models;

import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerModel extends UserModel {
	private static final long serialVersionUID = -5965129942424804047L;
	
	public WorkerModel(WorkerDTO workerDTO) {
		setWorkerDTO(workerDTO);
	}
	
	public void setWorkerDTO(WorkerDTO workerDTO) {
		super.setUserDTO(workerDTO);
	}
	
	public WorkerDTO getWorkerDTO() {
		return (WorkerDTO) getUserDTO();
	}

}
