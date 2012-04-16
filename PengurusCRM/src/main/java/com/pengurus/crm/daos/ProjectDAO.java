package com.pengurus.crm.daos;

import java.util.List;

import com.pengurus.crm.entities.Project;

public interface ProjectDAO extends GenericDAO<Project>{

    Project getById(long id);
    Project read(long id);
	List<Project> loadAllByExpertId(Long id);
	List<Project> loadAllByProjectManagerId(Long id);
	Project loadAllByTaskId(Long id);
	List<Project> loadAllBySupervisorId(Long id);
	
}