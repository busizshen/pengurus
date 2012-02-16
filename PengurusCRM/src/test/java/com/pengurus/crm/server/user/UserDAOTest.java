package com.pengurus.crm.server.user;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.aspectj.lang.annotation.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOTest {

	@Autowired
	UserDAO userDAO;
	
	@Before
	public void before(){
		Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
		userRoles.add(UserRoleDTO.ROLE_USER);
		userRoles.add(UserRoleDTO.ROLE_CLIENT);
		User user = new User(userRoles, "TestUserFromBefore", "password", "some description");
		userDAO.create(user);
	}
	
	@Test
	public void read() {
		Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
		userRoles.add(UserRoleDTO.ROLE_USER);
		userRoles.add(UserRoleDTO.ROLE_CLIENT);
		
		User user = userDAO.read(userDAO.findByUsername("TestUserFromBefore").getId());
		Assert.assertEquals(userRoles, user.getAuthorities());		
	}
	
	@Test
	public void update() {
		User user = userDAO.read(userDAO.findByUsername("TestUserFromBefore").getId());
		user.setDescription("new description");
		userDAO.update(user);
		user = userDAO.read(userDAO.findByUsername("TestUserFromBefore").getId());
		Assert.assertEquals("new description", user.getDescription());
	}
	
	@After(value = "")
	public void cleanUp(){
		User user = userDAO.findByUsername("TestUserFromBefore");
		if(user != null)
			userDAO.delete(user);
	}

}
