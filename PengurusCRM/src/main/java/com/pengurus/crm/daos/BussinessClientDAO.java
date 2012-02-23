package com.pengurus.crm.daos;

import com.pengurus.crm.entities.BussinessClient;

public interface BussinessClientDAO extends GenericDAO<BussinessClient>{
	
	public BussinessClient findByUsername(String username);

}
