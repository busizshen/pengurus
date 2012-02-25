package com.pengurus.crm.server;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.google.gwt.dev.util.collect.HashSet;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.exceptions.ServiceException;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

@ContextConfiguration(locations = { "testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ReflectionSaltSource saltSource;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	@Test(expected = ServiceException.class)
	public void abstractUserCreating() throws ServiceException {
		// when
		userService.createUser(new UserDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestAbstractUserCreating", "pass", "Decrtiption"));
	}
	
	@Test public void userCreating() throws ServiceException {
		// when
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_USER);
		userService.createUser(new WorkerDTO(null, roles, "userServiceTestUserCreating", "pass", "Decrtiption", new PersonalDataDTO()));

		roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_USER);
		userService.createUser(new WorkerDTO(null, roles, "user", "pass", "Decrtiption", new PersonalDataDTO()));

		// then
		Assert.notNull(userService.getUser("userServiceTestUserCreating"));
	}
	
	@Test public void passwordEncodingWhileCreating() throws ServiceException {
		// given
		PasswordEncoder encoder = new ShaPasswordEncoder();
		
		// when
		userService.createUser(new WorkerDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestPasswordEncodingCreating", "pass", "Decrtiption", new PersonalDataDTO()));
		
		// then
		UserDTO userDTO = userService.getUser("userServiceTestPasswordEncodingCreating");
		Assert.isTrue(encoder.encodePassword("pass", saltSource.getSalt(new User(userDTO).toUserDetails())).equals(userDTO.getPassword()));
	}
	
	@Test public void passwordEncodingWhileUpdating() throws ServiceException {
		// given
		UserDTO userDTO = new WorkerDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestPasswordEncodingUpdating", "pass", "Decrtiption", new PersonalDataDTO());
		userService.createUser(userDTO);
		userDTO.setPassword("updated");
		
		// when
		userService.updateUserWithPassword(userDTO);
		
		// then
		UserDTO newUserDTO = userService.getUser("userServiceTestPasswordEncodingUpdating");
		Assert.isTrue(passwordEncoder.encodePassword("updated", saltSource.getSalt(new User(newUserDTO).toUserDetails())).equals(userDTO.getPassword()));
	}
	
}
