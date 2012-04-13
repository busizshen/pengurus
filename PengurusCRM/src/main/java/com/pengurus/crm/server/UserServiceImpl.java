package com.pengurus.crm.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
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
import com.pengurus.crm.shared.pagination.PaginationUtil;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

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
		return userDAO.getUserByUsername(username).toUserDTO();
	}

	private Void updateUserHelper(UserDTO userDTO) throws ServiceException {
		if (userDTO instanceof IndividualClientDTO) {
			userDAO.update(new IndividualClient((IndividualClientDTO) userDTO));
		} else if (userDTO instanceof BusinessClientDTO) {
			userDAO.update(new BusinessClient((BusinessClientDTO) userDTO));
		} else if (userDTO instanceof WorkerDTO) {
			userDAO.update(new Worker((WorkerDTO) userDTO));
		} else if (userDTO instanceof TranslatorDTO) {
			userDAO.update(new Translator((TranslatorDTO) userDTO));
		} else {
			throw new ServiceException();
		}
		return null;
	}
	
	@Override
	public Void updateUser(UserDTO userDTO) throws ServiceException {
		User oldUser = userDAO.read(userDTO.getId());
		userDTO.setPassword(oldUser.getPassword());
		return updateUserHelper(userDTO);
	}

	@Override
	public Void updateUserWithPassword(UserDTO userDTO) throws ServiceException {
		encodePassword(userDTO);
		return updateUserHelper(userDTO);
	}
	@Override
	public Void createUser(UserDTO userDTO) throws ServiceException, UsernameExistsException {
		if (userDAO.usernameExists(userDTO.getUsername())) {
			throw new UsernameExistsException();
		}
		encodePassword(userDTO);
		User user;
		if (userDTO instanceof IndividualClientDTO) {
			user = new IndividualClient((IndividualClientDTO)userDTO);
		} else if(userDTO instanceof BusinessClientDTO) {
			user = new BusinessClient((BusinessClientDTO)userDTO);
		} else if(userDTO instanceof TranslatorDTO) {
			user = new Translator((TranslatorDTO)userDTO);
		} else if(userDTO instanceof WorkerDTO) {
			user = new Worker((WorkerDTO)userDTO);
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
	public Set<UserDTO> getAllUsers() {
		List<User> list = userDAO.getAll();
		Set<UserDTO> set = new HashSet<UserDTO>();
		for(User user: list){
			set.add(user.toDTO());
		}
		return set;
	}

	@Override
	public Set<UserDTO> getUsersByRoles(Set<UserRoleDTO> roles) {
		List<User> list = userDAO.getAll();
		Set<UserDTO> set = new HashSet<UserDTO>();
		for (User u : list) {
			for (UserRoleDTO role : roles) {
				if (u.getAuthorities().contains(role)){
					set.add(u.toDTO());
					break;
				}
			}
		}
		return set;
	}
	
	
	
	@Override
	public PagingLoadResultHelper<UserModel> getPaginatedAllUsers(PagingLoadConfigHelper loadConfig) {
		ArrayList<UserModel> userModelList = new ArrayList<UserModel>();
		for (UserDTO user:getAllUsers()) {
			userModelList.add(new UserModel(user));
		}
		return PaginationUtil.paginate(loadConfig, userModelList);
	}

	@Override
	public PagingLoadResultHelper<UserModel> getPaginatedUsersByRoles(
			PagingLoadConfigHelper loadConfig, Set<UserRoleDTO> roles) {
		ArrayList<UserModel> userModelList = new ArrayList<UserModel>();
		for (UserDTO user:getUsersByRoles(roles)) {
			userModelList.add(new UserModel(user));
		}
		return PaginationUtil.paginate(loadConfig, userModelList);
	}
}
