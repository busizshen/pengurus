package com.pengurus.crm.server.crud;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserCRUDTest {

	@Autowired
	private UserDAO userDAO;

	private Set<UserRoleDTO> userRoleDTO;

	@Before
	public void before() {
		userRoleDTO = new HashSet<UserRoleDTO>();
		userRoleDTO.add(UserRoleDTO.ROLE_USER);
		userRoleDTO.add(UserRoleDTO.ROLE_EXPERT);
	}

	@Test
	public void simpleCreateUser() {
		Worker user = new Worker(userRoleDTO, "Test", "password",
				"description", new PersonalData("James", "Smith", "London",
						null, null));
		userDAO.create(user);
		Assert.assertNotNull(user.getId());
	}

	@Test
	public void simpleGetUser() {
		// prepare data
		Worker user = new Worker(userRoleDTO, "Killer", "password",
				"description", new PersonalData("Jacob", "Smith", "London",
						null, null));
		userDAO.create(user);

		// test
		User userFromDB = userDAO.read(user.getId());
		Assert.assertEquals("Killer", userFromDB.getUsername());
		Assert.assertEquals("description", userFromDB.getDescription());
		Assert.assertEquals("password", userFromDB.getPassword());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void simpleUpdateUser() {
		// prepare data
		Worker user = new Worker(userRoleDTO, "User12", "password",
				"description", new PersonalData("James", "Smudge", "London",
						null, null));
		userDAO.create(user);

		// test
		User userFromDB = userDAO.read(user.getId());
		userFromDB.setDescription("new description");
		userFromDB.setUsername("new name");
		userDAO.update(userFromDB);

		User newUserFromDB = userDAO.getUserByUsername("new name");
		Assert.assertEquals("new description", newUserFromDB.getDescription());
		userDAO.getUserByUsername("User12");
	}

	@Test(expected = UsernameNotFoundException.class)
	public void simpleDeleteUser() {
		// prepare data
		Worker user = new Worker(userRoleDTO, "master", "password",
				"description", new PersonalData("James", "Cole", "London",
						null, null));
		userDAO.create(user);

		// test
		userDAO.delete(user);
		userDAO.getUserByUsername("master");
	}

	@Test(expected = DataAccessException.class)
	public void addUsersWithTheSameUsername() {
		Worker user = new Worker(userRoleDTO, "master", "password",
				"description", new PersonalData("James", "Smith", "London",
						null, null));
		Worker user2 = new Worker(userRoleDTO, "master", "password",
				"description", new PersonalData("James", "Smith", "London",
						null, null));
		userDAO.create(user);
		userDAO.create(user2);
	}

}
