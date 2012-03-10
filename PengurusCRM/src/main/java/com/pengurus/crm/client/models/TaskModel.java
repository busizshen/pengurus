package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845030826361147243L;
	
	private TaskDTO taskDTO;

	public TaskModel(TaskDTO taskDTO){
		super();
		setTaskDTO(taskDTO);
	}
	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setTaskDTO(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
	}

}
