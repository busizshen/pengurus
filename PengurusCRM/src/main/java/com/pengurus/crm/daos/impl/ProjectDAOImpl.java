package com.pengurus.crm.daos.impl;

import java.util.List;

import org.hibernate.Query;
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

    public Project getById(long id){
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> loadAllByExpertId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Long[] ids = {id};
		String hql = "select distinct p from Project p " +
		                "join p.experts e " +
		                "where e.id in (:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", ids);
		List<Project> projects = query.list();
		return projects;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> loadAllByProjectManagerId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Long[] ids = {id};
		String hql = "select distinct p from Project p " +
		                "join p.projectManagers pm " +
		                "where pm.id in (:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", ids);
		List<Project> projects = query.list();
		return projects;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> loadAllByTaskId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Long[] ids = {id};
		String hql = "select distinct p from Project p " +
		                "join p.jobs j join j.task t " +
		                "where t.id in (:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", ids);
		List<Project> projects = query.list();
		return projects;
	}
	
}