package com.pengurus.crm.daos;

import com.pengurus.crm.daos.GenericDAO;
import com.pengurus.crm.entities.IndividualClient;

public interface IndividualClientDAO extends GenericDAO<IndividualClient>{
	
	public IndividualClient findByUsername(String username);

}
