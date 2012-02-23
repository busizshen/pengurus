package com.pengurus.crm.daos;

import com.pengurus.crm.entities.Client;

public interface ClientDAO extends GenericDAO<Client>{
	
	public Client findByUsername(String username);

}
