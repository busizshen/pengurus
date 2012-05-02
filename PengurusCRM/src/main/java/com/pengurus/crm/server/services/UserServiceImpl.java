package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.UserUtils;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class UserServiceImpl implements UserService {

	protected static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public UserDTO getUser(String username) {
		return userDAO.getUserByUsername(username).toDTO();
	}

	private Void updateUserHelper(UserDTO userDTO) throws ServiceException {
		userDAO.update(UserUtils.toUser(userDTO));
		return null;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE') or hasPermission(#userDTO, 'write')")
	public Void updateUser(UserDTO userDTO) throws ServiceException {
		User oldUser = userDAO.read(userDTO.getId());
		userDTO.setPassword(oldUser.getPassword());
		return updateUserHelper(userDTO);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE') or hasPermission(#userDTO, 'write')")
	public Void updateUserWithPassword(UserDTO userDTO) throws ServiceException {
		encodePassword(userDTO);
		return updateUserHelper(userDTO);
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public Void createUser(UserDTO userDTO) throws ServiceException, UsernameExistsException {
		if (userDAO.usernameExists(userDTO.getUsername())) {
			throw new UsernameExistsException();
		}
		encodePassword(userDTO);
		userDAO.create(UserUtils.toUser(userDTO));
		return null;
	}

	private void encodePassword(UserDTO userDTO) {
		String encodedPassword = passwordEncoder.encodePassword(userDTO.getPassword(), saltSource.getSalt(UserUtils.toUser(userDTO).toUserDetails()));
		userDTO.setPassword(encodedPassword);
	}

	@Override
	public Boolean checkPassword(String currentPassword, UserDTO userDTO) {
		String password = userDTO.getPassword();
		userDTO.setPassword(currentPassword);
		encodePassword(userDTO);
		return (userDTO.getPassword().equals(password));
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public Set<UserDTO> getAllUsers() {
		List<User> list = userDAO.getAll();
		Set<UserDTO> set = new HashSet<UserDTO>();
		for(User user: list){
			set.add(user.toDTO());
		}
		return set;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public Set<UserDTO> getUsersByRoles(Set<UserRoleDTO> roles) {
		List<User> list = userDAO.getAll();
		Set<UserDTO> set = new HashSet<UserDTO>();
		for (User user : list) {
			for (UserRoleDTO role : roles) {
				if (user.getAuthorities().contains(role)){
					set.add(user.toDTO());
					break;
				}
			}
		}
		return set;
	}
}
