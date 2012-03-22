package com.pengurus.crm.daos.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.JobDAO;
import com.pengurus.crm.entities.Job;

public class JobDAOImpl extends GenericDAOImpl<Job> implements JobDAO {

    protected static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);
    
    public JobDAOImpl(){
        this.type = Job.class;
    }

	@Override
	public Job getById(Long id) {
        try{
            Session session = getHibernateTemplate().getSessionFactory().openSession();
            Job job= (Job) session.get(type, id);
            job.getTask().size();
            session.close();
            return job;
        } catch(Exception e) {
            return null;
        }
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<Job> loadAllByExpertId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select distinct j from Job j " +
                "join j.task t " +
                "where t.expert = " + id;
		Query query = session.createQuery(hql);
		List<Job> jobs = query.list();
		return jobs;
	}
	
}
