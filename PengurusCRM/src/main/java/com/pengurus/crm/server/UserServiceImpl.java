package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.BusinessClient;
import com.pengurus.crm.entities.IndividualClient;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.IndividualClientDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

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
	public UserDTO getUser(String username) {
		return userDAO.findByUsername(username).toUserDTO();
	}

	@Override
	public Void updateUser(UserDTO userDTO) {
		userDAO.update(new User(userDTO));
		return null;
	}

	@Override
	public Void updateUserWithPassword(UserDTO userDTO) {
		encodePassword(userDTO);
		return updateUser(userDTO);
	}
	@Override
	public Void createUser(UserDTO userDTO) throws ServiceException {
		encodePassword(userDTO);
		User user;
		if (userDTO instanceof IndividualClientDTO) {
			user = new IndividualClient((IndividualClientDTO)userDTO);
		} else if(userDTO instanceof BusinessClientDTO) {
			user = new BusinessClient((BusinessClientDTO)userDTO);
		} else if(userDTO instanceof WorkerDTO) {
			user = new Worker((WorkerDTO)userDTO);
		} else if(userDTO instanceof TranslatorDTO) {
			user = new Translator((TranslatorDTO)userDTO);
		} else {
			throw new ServiceException();
		}
		userDAO.create(user);
		return null;
	}

	private void encodePassword(UserDTO user) {
		String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(new User(user).toUserDetails()));
		user.setPassword(encodedPassword);
	}

	@Override
	public Boolean checkPassword(String currentPassword, UserDTO userDTO) {
		String password = userDTO.getPassword();
		userDTO.setPassword(currentPassword);
		encodePassword(userDTO);
		return (userDTO.getPassword().equals(password));
	}

	@Override
	public Set<UserDTO> getUsersByRoles(Set<UserRoleDTO> roles) {
		// TODO [CRM-30]
		return new HashSet<UserDTO>();
	}

	@Override
	public Set<UserDTO> getAllUsers() {
		// TODO [CRM-30]
		return new HashSet<UserDTO>();
	}
}
