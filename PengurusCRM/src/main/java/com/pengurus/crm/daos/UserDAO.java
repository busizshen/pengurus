package com.pengurus.crm.daos;

import com.pengurus.crm.entities.User;
import com.pengurus.crm.daos.GenericDAO;

public interface UserDAO extends GenericDAO<User>{
	
	public User findByUsername(String name);

}
