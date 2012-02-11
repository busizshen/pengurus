package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.BussinessClientDAO;
import com.pengurus.crm.entities.BussinessClient;

public class BussinessClientDAOImpl extends GenericDAOImpl<BussinessClient> implements BussinessClientDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(BussinessClientDAOImpl.class);
	
	public BussinessClientDAOImpl(){
		this.type = BussinessClient.class;
	}

	@Override
	public BussinessClient findByUsername(String username) {
		try{
			String query = "select c from BussinessClient c where c.username = '" + username + "'";
			return (BussinessClient) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
