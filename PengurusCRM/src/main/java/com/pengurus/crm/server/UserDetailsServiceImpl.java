package com.pengurus.crm.server;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
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
		User user = userDAO.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		return user;
	}
}