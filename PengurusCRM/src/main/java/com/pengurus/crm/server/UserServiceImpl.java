package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private PasswordEncoder passwordEncoder;
	private ReflectionSaltSource saltSource;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ReflectionSaltSource getSaltSource() {
		return saltSource;
	}

	public void setSaltSource(ReflectionSaltSource saltSource) {
		this.saltSource = saltSource;
	}


	@Override
	public UserDTO getUser(String username) {
		return userDAO.findByUsername(username).toUserDTO();
	}

	@Override
	public Void updateUser(UserDTO user) {
		userDAO.update(new User(user));
		return null;
	}

	@Override
	public Void updateUserWithPassword(UserDTO user) {
		encodePassword(user);
		return updateUser(user);
	}
	@Override
	public Void createUser(UserDTO user) {
		encodePassword(user);
		userDAO.create(new User(user));
		return null;
	}

	private void encodePassword(UserDTO user) {
		String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(new User(user).toUserDetails()));
		user.setPassword(encodedPassword);
	}

	@Override
	public Boolean checkPassword(String currentPassword, UserDTO user) {
		String password = user.getPassword();
		user.setPassword(currentPassword);
		encodePassword(user);
		return (user.getPassword().equals(password));
	}

	@Override
	public Set<UserDTO> getUsersByRole(Set<UserRoleDTO> roles) {
		return new HashSet<UserDTO>();
	}
}
