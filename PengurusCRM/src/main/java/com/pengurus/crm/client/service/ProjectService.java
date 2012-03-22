package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.ProjectDTO;

@RemoteServiceRelativePath("project.rpc")
public interface ProjectService extends RemoteService {
	
	public Set<ProjectDTO> getProjects();
	public ProjectDTO createProject(ProjectDTO projectDTO);
	public ProjectDTO getProject(Long id);
	public Set<ProjectDTO> getProjectByExpertId(Long id);
	public Set<ProjectDTO> getProjectByProjectManagerId(Long id);
	
}
