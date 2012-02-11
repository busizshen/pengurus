package com.pengurus.crm.daos;

import com.pengurus.crm.daos.GenericDAO;
import com.pengurus.crm.entities.User;

public interface UserDAO extends GenericDAO<User>{
	
	public User findByUsername(String username);

}
