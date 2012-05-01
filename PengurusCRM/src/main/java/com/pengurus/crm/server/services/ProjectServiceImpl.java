package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.daos.ProjectDAO;
import com.pengurus.crm.entities.Project;
import com.pengurus.crm.shared.dto.ProjectDTO;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;

	protected static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public Set<ProjectDTO> getAllProjects() {
		List<Project> list = projectDAO.loadAll();
		Set<ProjectDTO> set = new HashSet<ProjectDTO>();
		for (Project p : list) {
			set.add(p.toDTOLazy());
		}
		return set;
	}

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		projectDTO.setId(projectDAO.create(new Project(projectDTO)));
		return projectDTO;
	}

	@Override
	public ProjectDTO getProject(Long id) {
		return projectDAO.getById(id).toDTO();
	}

	@Override
	public Set<ProjectDTO> getProjectsByExpertId(Long id) {
		List<Project> list = projectDAO.loadAllByExpertId(id);
		Set<ProjectDTO> set = new HashSet<ProjectDTO>();
		for (Project p : list) {
			set.add(p.toDTOLazy());
		}
		return set;
	}

	@Override
	public Set<ProjectDTO> getProjectsByProjectManagerId(Long id) {
		List<Project> list = projectDAO.loadAllByProjectManagerId(id);
		Set<ProjectDTO> set = new HashSet<ProjectDTO>();
		for (Project p : list) {
			set.add(p.toDTOLazy());
		}
		return set;
	}

	@Override
	public void updateProject(ProjectDTO projectDTO) {
		projectDAO.update(new Project(projectDTO));

	}

	@Override
	public void deleteProject(ProjectDTO projectDTO) {
		projectDAO.delete(new Project(projectDTO));

	}

	@Override
	public ProjectDTO getProjectByTaskId(Long id) {
		Project p = projectDAO.loadAllByTaskId(id);
		if(p == null)
			return null;
		return p.toDTO();
	}

	@Override
	public Set<ProjectDTO> getProjectsBySupervisorId(Long id) {
		List<Project> list = projectDAO.loadAllBySupervisorId(id);
		Set<ProjectDTO> set = new HashSet<ProjectDTO>();
		for (Project p : list) {
			set.add(p.toDTOLazy());
		}
		return set;
	}
}
