package com.pengurus.crm.daos.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.TaskDAO;
import com.pengurus.crm.entities.Task;

public class TaskDAOImpl extends GenericDAOImpl<Task> implements TaskDAO{
    
    protected static final Logger log = LoggerFactory.getLogger(TaskDAOImpl.class);
    
    public TaskDAOImpl(){
        this.type = Task.class;
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Task> loadAllByExpertId(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select t from Task t " +
				"where t.expert = " + id;
		Query query = session.createQuery(hql);
		List<Task> tasks = query.list();
		return tasks;
	}
}
