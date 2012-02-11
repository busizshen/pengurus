package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.IndividualClientDAO;
import com.pengurus.crm.entities.IndividualClient;

public class IndividualClientDAOImpl extends GenericDAOImpl<IndividualClient> implements IndividualClientDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(IndividualClientDAOImpl.class);
	
	public IndividualClientDAOImpl(){
		this.type = IndividualClient.class;
	}

	@Override
	public IndividualClient findByUsername(String username) {
		try{
			String query = "select c from IndividualClient c where c.username = '" + username + "'";
			return (IndividualClient) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
