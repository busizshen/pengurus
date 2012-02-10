package com.pengurus.crm.server;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.daos.UserRoleDAO;
import com.pengurus.crm.shared.User;
import com.pengurus.crm.shared.UserRole;

@ContextConfiguration(locations = {"classpath:com/pengurus/crm/server/testContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleTest {
	
	@Autowired
	private UserDAO ud;
	
	@Autowired
	private UserRoleDAO urd;
	
	@Autowired
	private AuthorizationService authService;
	
	private Long id;
	
	@Before 
	public void createUser(){
		HashSet<UserRole> li = new HashSet<UserRole>();
		UserRole ur = UserRole.ROLE_USER;
		urd.create(ur);
		li.add(ur);
		User u = new User(li, "Username", "pass", "descr");
		Assert.assertNotNull(u);
		id = ud.create(u);
		Assert.assertTrue(id!=-1);
		User u2 = new User(li, "Username2", "pass", "descr2");
		id = ud.create(u2);
	}
	
	@Test
	public void logIn(){
		authService.login("Username", "pass");
		Assert.assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
	}
	

}
