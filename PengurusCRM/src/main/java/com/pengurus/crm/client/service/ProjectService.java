package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.ProjectDTO;

@RemoteServiceRelativePath("project.rpc")
public interface ProjectService extends RemoteService {
	
	public ProjectDTO createProject(ProjectDTO projectDTO);
	public void updateProject(ProjectDTO projectDTO);
	public void deleteProject(ProjectDTO projectDTO);

	public ProjectDTO getProject(Long id);
	public ProjectDTO getProjectByTaskId(Long id);

	public Set<ProjectDTO> getAllProjects();
	public Set<ProjectDTO> getProjectsByExpertId(Long id);
	public Set<ProjectDTO> getProjectsByProjectManagerId(Long id);
	public Set<ProjectDTO> getProjectsBySupervisorId(Long id);
}
