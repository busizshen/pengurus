package com.pengurus.crm.server;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengurus.crm.daos.UserDAO;

@SuppressWarnings("deprecation")
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	protected static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
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
	
		try {
			log.error("zz"+userDAO.toString()+"zz\n");
			log.error("\n--AA--\n" + username + "\n");
		} catch (NullPointerException e) {
			log.error("null");
		}
		com.pengurus.crm.entities.User userEntity = userDAO.findByUsername(username);
		
		log.error("\n--AA2--\n" + username + "\n");
		if (userEntity == null){
			log.error("\n--AA3--\n" + "You're not form here :(" + "\n");
			throw new UsernameNotFoundException("user not found");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		log.error("\n--BB--\n" + username + "\n");
		
		return new User(username, userEntity.getPassword(), true, true, true, true, authorities);
	}
}