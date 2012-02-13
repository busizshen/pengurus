package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.ProjectDAO;
import com.pengurus.crm.entities.Project;

public class ProjectDAOImpl extends GenericDAOImpl<Project> implements
        ProjectDAO {

    protected static final Logger log = LoggerFactory
            .getLogger(ProjectDAOImpl.class);

    public ProjectDAOImpl() {
        this.type = Project.class;
    }

}