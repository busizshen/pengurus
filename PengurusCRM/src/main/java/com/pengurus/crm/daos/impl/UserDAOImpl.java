package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;


public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	public UserDAOImpl(){
		this.type = User.class;
	}
	
	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		try {
			String query = "select u from User u where u.username = '" + username + "'";
			return (User) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}
}
