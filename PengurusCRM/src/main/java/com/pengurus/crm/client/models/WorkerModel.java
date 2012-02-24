package com.pengurus.crm.client.models;

import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerModel extends UserModel {

	private static final long serialVersionUID = -5965129942424804047L;
	private WorkerDTO workerDTO;
	
	public WorkerModel(WorkerDTO q) {
		setWorkerDTO(workerDTO);
	}
	public void setWorkerDTO(WorkerDTO workerDTO) {
		this.workerDTO = workerDTO;
		if(workerDTO.getId() != null)
			set("id",workerDTO.getId());
		if(workerDTO.getUsername() != null){
			set("username",workerDTO.getUsername());
		}
		if(workerDTO.getDescription() != null){
			set("description",workerDTO.getDescription());
		}
	}
	public WorkerDTO getWorkerDTO() {
		return this.workerDTO;
	}

}
