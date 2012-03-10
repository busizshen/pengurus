package com.pengurus.crm.daos.impl;

import org.hibernate.Session;
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

    public Project read(long id){
        try{
            Session session = getHibernateTemplate().getSessionFactory().openSession();
            Project project = (Project) session.get(type, id);
            if(project.getJobs()!= null)
            	project.getJobs().size();
            if(project.getExperts()!= null)
            	project.getExperts().size();
            if(project.getFreelancers()!= null)  
            	project.getFreelancers().size();
            if(project.getProjectManagers()!= null)
            	project.getProjectManagers().size();
            session.close();
            return project;
        } catch(Exception e) {
            return null;
        }
    }
}