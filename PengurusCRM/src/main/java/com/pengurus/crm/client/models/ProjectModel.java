package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934670530119679714L;

	private ProjectDTO projectDTO;
	
	public ProjectModel(ProjectDTO	projectDTO){
		this.setProjectDTO(projectDTO);
	}
	
	public void setProjectDTO(ProjectDTO projectDTO){
		this.projectDTO = projectDTO;
		set("id", projectDTO.getId());
		if(projectDTO.getStatus() != null){
			set("status", projectDTO.getStatus());
		}
		if(projectDTO.getSupervisor() != null){
			set("supervisor",projectDTO.getSupervisor().getFullName());
		}
		if(projectDTO.getClient() != null){
			set("client", projectDTO.getClient().getFullName());
		}
		if(projectDTO.getDescription() != null){
			set("description", projectDTO.getDescription());
		}
	}
	
	public String getStatus(){
		return get("status");
	}
	
	public String getClient(){
		return get("client");
	}
	
	public String getSupervisor(){
		return get("supervisor");
	}
	
	public String getDescription(){
		return get("descritpion");
	}

	public ProjectDTO getProjectDTO() {
		return this.projectDTO;
	}
	
	
}

