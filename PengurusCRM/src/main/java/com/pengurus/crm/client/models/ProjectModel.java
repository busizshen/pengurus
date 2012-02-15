package com.pengurus.crm.client.models;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934670530119679714L;

	private ProjectDTO projectDTO;
	
	public void setProjectDTO(ProjectDTO projectDTO){
		this.projectDTO = projectDTO;
	}
	
}
