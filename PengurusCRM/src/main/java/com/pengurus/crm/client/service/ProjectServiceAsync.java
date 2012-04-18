package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public interface ProjectServiceAsync {
	
	public void createProject(ProjectDTO projectDTO, AsyncCallback<ProjectDTO> callback);
	public void updateProject(ProjectDTO projectDTO, AsyncCallback<Void> callback);
	public void deleteProject(ProjectDTO projectDTO, AsyncCallback<Void> callback);
	
	public void getProject(Long id, AsyncCallback<ProjectDTO> callback);
	public void getProjectByTaskId(Long id, AsyncCallback<ProjectDTO> callback);

	public void getAllProjects(AsyncCallback<Set<ProjectDTO>> callback);
	public void getProjectsByExpertId(Long id, AsyncCallback<Set<ProjectDTO>> callback);
	public void getProjectsByProjectManagerId(Long id, AsyncCallback<Set<ProjectDTO>> callback);
	public void getProjectsBySupervisorId(Long id, AsyncCallback<Set<ProjectDTO>> callback);

	public void getPaginatedAllProjects(PagingLoadConfigHelper loadConfig, 
			AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsByExpertId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsByProjectManagerId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	public void getPaginatedProjectsBySupervisorId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<ProjectModel>> callback);
	
}
