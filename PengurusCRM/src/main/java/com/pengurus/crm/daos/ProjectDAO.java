package com.pengurus.crm.daos;

import com.pengurus.crm.entities.Project;

public interface ProjectDAO extends GenericDAO<Project>{

    Project read(long id);
}