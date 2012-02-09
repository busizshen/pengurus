package com.pengurus.crm.daos.impl;

import com.pengurus.crm.daos.UserRoleDAO;
import com.pengurus.crm.enums.UserRole;

public class UserRoleDAOImpl extends GenericDAOImpl<UserRole> implements UserRoleDAO {
	
	public UserRoleDAOImpl(){
		this.type = UserRole.class;
	}
}
