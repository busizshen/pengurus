package com.pengurus.crm.server;

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
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

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

	@Test public void userCreating() {
		// when
		userService.createUser(new UserDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestUserCreating", "pass", "Decrtiption"));
		
		// then
		Assert.notNull(userService.getUser("userServiceTestUserCreating"));
	}
	
	@Test public void passwordEncodingWhileCreating() {
		// given
		PasswordEncoder encoder = new ShaPasswordEncoder();
		userService.createUser(new UserDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestPasswordEncodingCreating", "pass", "Decrtiption"));
		
		// when
		UserDTO userDTO = userService.getUser("userServiceTestPasswordEncodingCreating");
		
		// then
		Assert.isTrue(encoder.encodePassword("pass", saltSource.getSalt(new User(userDTO).toUserDetails())).equals(userDTO.getPassword()));
	}
	
	@Test public void passwordEncodingWhileUpdating() {
		// given
		UserDTO userDTO = new UserDTO(null, new HashSet<UserRoleDTO>(), "userServiceTestPasswordEncodingUpdating", "pass", "Decrtiption");
		userService.createUser(userDTO);
		userDTO.setPassword("updated");
		userService.updateUserWithPassword(userDTO);
		
		// when
		userDTO = userService.getUser("userServiceTestPasswordEncodingCreating");
		
		// then
		Assert.isTrue(passwordEncoder.encodePassword("updated", saltSource.getSalt(new User(userDTO).toUserDetails())).equals(userDTO.getPassword()));
	}
	
}
