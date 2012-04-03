package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.WorkerDAO;
import com.pengurus.crm.entities.Worker;


public class WorkerDAOImpl extends GenericDAOImpl<Worker> implements WorkerDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(WorkerDAOImpl.class);
	
	public WorkerDAOImpl(){
		this.type = Worker.class;
	}
	
	@Override
	public Worker findByUsername(String username) {
		try{
			String query = "select w from Worker w where w.username = '" + username + "'";
			return (Worker) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
