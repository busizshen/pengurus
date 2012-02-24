package com.pengurus.crm.daos;

import com.pengurus.crm.entities.BusinessClient;

public interface BusinessClientDAO extends GenericDAO<BusinessClient>{
	
	public BusinessClient findByUsername(String username);

}
