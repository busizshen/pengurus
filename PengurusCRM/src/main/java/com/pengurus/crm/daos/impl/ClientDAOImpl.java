package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.ClientDAO;
import com.pengurus.crm.entities.Client;

public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(ClientDAOImpl.class);
	
	public ClientDAOImpl(){
		this.type = Client.class;
	}

	@Override
	public Client findByUsername(String username) {
		try{
			String query = "select c from Client c where c.username = '" + username + "'";
			return (Client) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
