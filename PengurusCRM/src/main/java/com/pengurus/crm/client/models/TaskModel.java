package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845030826361147243L;

	private TaskDTO taskDTO;

	public TaskModel(TaskDTO taskDTO) {
		super();
		setTaskDTO(taskDTO);
	}

	public TaskDTO getTaskDTO() {
		return taskDTO;
	}

	public void setTaskDTO(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
		set("id", this.taskDTO.getId().toString());
		set("description", this.taskDTO.getDescription());
		if (this.taskDTO.getStatus() != null)
			set("status", this.taskDTO.getStatus().toString());
		if (this.taskDTO.getDeadline() != null)
			set("dealine", this.taskDTO.getDeadline().toString());
		if (this.taskDTO.getExpert() != null)
			set("expert", this.taskDTO.getExpert().getFullName());
		// weryfikator
		// if(this.taskDTO.get != null)
		// set("expert", this.taskDTO.getExpert().getFullName());
		if (this.taskDTO.getTranslation() != null) {
			set("translationFrom", this.taskDTO.getTranslation().getFrom()
					.getLanguage());
			set("translationTo", this.taskDTO.getTranslation().getTo()
					.getLanguage());
		}
	}

}
