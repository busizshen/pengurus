package com.pengurus.crm.server;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.shared.User;
import com.pengurus.crm.shared.UserRole;

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

	private UserDetails createUserDetailsFromUser(User user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (UserRole userRole : user.getAuthorities()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole
					.toString()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), grantedAuthorities);
		return userDetails;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user = userDAO.findByUsername(username);
		log.error(user.toString());
		return createUserDetailsFromUser(user);
	}
}