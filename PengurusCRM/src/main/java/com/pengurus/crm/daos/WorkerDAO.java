package com.pengurus.crm.daos;

import com.pengurus.crm.daos.GenericDAO;
import com.pengurus.crm.entities.Worker;

public interface WorkerDAO extends GenericDAO<Worker>{
	
	public Worker findByUsername(String username);

}
