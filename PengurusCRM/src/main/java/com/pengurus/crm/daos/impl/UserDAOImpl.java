package com.pengurus.crm.daos.impl;

import java.util.ArrayList;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;


public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
	
	public UserDAOImpl(){
		this.type = User.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String name) {
		return (User) ((ArrayList<User>) getHibernateTemplate().find("select u from User u where u.username = '" + name + "'")).get(0);
	}
	
/*	@Override
	public boolean delete(User u){
		authService.startAdminMode();
		getHibernateTemplate().deleteAll(getHibernateTemplate().find("select ai from AnnouncementInstance ai where receiver=" + u.getUsername()));
		getHibernateTemplate().deleteAll(getHibernateTemplate().find("select ap from ACLPermissions ap where userId=" + u.getUsername()));
		boolean result = super.delete(u);
		authService.leaveAdminMode();
		return result;
	}
*/	
}
