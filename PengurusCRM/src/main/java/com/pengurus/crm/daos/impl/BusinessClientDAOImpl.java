package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.BusinessClientDAO;
import com.pengurus.crm.entities.BusinessClient;

public class BusinessClientDAOImpl extends GenericDAOImpl<BusinessClient> implements BusinessClientDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(BusinessClientDAOImpl.class);
	
	public BusinessClientDAOImpl(){
		this.type = BusinessClient.class;
	}

	@Override
	public BusinessClient findByUsername(String username) {
		try{
			String query = "select c from BusinessClient c where c.username = '" + username + "'";
			return (BusinessClient) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
