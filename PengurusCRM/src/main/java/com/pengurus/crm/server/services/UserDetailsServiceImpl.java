package com.pengurus.crm.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	protected static final Logger log = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		User user = userDAO.getUserByUsername(username);
		log.error(user.toString());
		return user.toUserDetails();
		
	}
}