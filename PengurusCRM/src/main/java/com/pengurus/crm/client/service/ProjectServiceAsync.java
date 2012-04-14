package com.pengurus.crm.client.service;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.entities.Project;
import com.pengurus.crm.shared.dto.ProjectDTO;

public interface ProjectServiceAsync {
	
	public void getProjects(AsyncCallback<Set<ProjectDTO>> callback);
	public void createProject(ProjectDTO projectDTO, AsyncCallback<ProjectDTO> callback);
	public void updateProject(ProjectDTO projectDTO, AsyncCallback<Void> callback);
	public void deleteProject(ProjectDTO projectDTO, AsyncCallback<Void> callback);
	public void getProject(Long id, AsyncCallback<ProjectDTO> callback);
	public void getProjectByExpertId(Long id, AsyncCallback<ProjectDTO> callback);
	public void getProjectByProjectManagerId(Long id, AsyncCallback<ProjectDTO> callback);
	public void getProjectByTaskId(Long id, AsyncCallback<ProjectDTO> callback);
	
}
