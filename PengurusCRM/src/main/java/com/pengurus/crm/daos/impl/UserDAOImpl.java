package com.pengurus.crm.daos.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;


public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl(){
		this.type = User.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String name) {
		log.error("looking for Username named ::" + name);
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
