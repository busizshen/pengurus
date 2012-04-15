package com.pengurus.crm.server;

import java.util.Iterator;
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
import com.pengurus.crm.client.service.exceptions.UsernameExistsException;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.BusinessClientDTO;
import com.pengurus.crm.shared.dto.PersonalDataDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
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

	@Test(expected = NullPointerException.class)
	public void abstractUserCreating() throws ServiceException, UsernameExistsException { 
		// when
		userService.createUser(new UserDTO(null, new HashSet<UserRoleDTO>(),
				"userServiceTestAbstractUserCreating", "pass", "Decrtiption"));
	}

	@Test
	public void userCreating() throws ServiceException, UsernameExistsException { // when
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_USER);
		userService.createUser(new WorkerDTO(null, roles,
				"userServiceTestUserCreating", "pass", "Decrtiption",
				new PersonalDataDTO()));

		roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_USER);
		userService.createUser(new WorkerDTO(null, roles, "user", "pass",
				"Decrtiption", new PersonalDataDTO()));

		// then
		Assert.notNull(userService.getUser("userServiceTestUserCreating"));
	}

	@Test
	public void passwordEncodingWhileCreating() throws ServiceException, UsernameExistsException { 
		// given
		PasswordEncoder encoder = new ShaPasswordEncoder();
		// when
		userService.createUser(new WorkerDTO(null, new HashSet<UserRoleDTO>(),
				"userServiceTestPasswordEncodingCreating", "pass",
				"Decrtiption", new PersonalDataDTO()));

		// then
		UserDTO userDTO = userService
				.getUser("userServiceTestPasswordEncodingCreating");
		Assert.isTrue(encoder.encodePassword("pass",
				saltSource.getSalt(new User(userDTO).toUserDetails())).equals(
				userDTO.getPassword()));
	}

	@Test
	public void passwordEncodingWhileUpdating() throws ServiceException, UsernameExistsException { // given
		UserDTO userDTO = new WorkerDTO(null, new HashSet<UserRoleDTO>(),
				"userServiceTestPasswordEncodingUpdating", "pass",
				"Decrtiption", new PersonalDataDTO());
		userService.createUser(userDTO);
		userDTO.setPassword("updated");

		// when
		userService.updateUserWithPassword(userDTO);

		// then
		UserDTO newUserDTO = userService
				.getUser("userServiceTestPasswordEncodingUpdating");
		Assert.isTrue(passwordEncoder.encodePassword("updated",
				saltSource.getSalt(new User(newUserDTO).toUserDetails()))
				.equals(userDTO.getPassword()));
	}

	@Test
	public void getAll() throws ServiceException {
		Set<UserDTO> list = userService.getAllUsers();
		for (UserDTO u : list) {
			Assert.notNull(u.getAuthorities());
			verifyClients(list, null); // no roles to check so it's null
			verifyWorkers(list, null); // no roles to check so it's null
		}
	}

	@Test
	public void getAllByRoleCLIENT() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_CLIENT);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyClients(list, roles);
	}

	@Test
	public void getAllByRolePROJECTMANAGER() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyWorkers(list, roles);
	}

	@Test
	public void getAllByRoleEXPERT() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXPERT);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyWorkers(list, roles);
	}

	@Test
	public void getAllByRoleEXECUTIVE() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyWorkers(list, roles);
	}

	@Test
	public void getAllByRoleFREELANCER() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_FREELANCER);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyWorkers(list, roles);
	}

	@Test
	public void getAllByRoles() throws ServiceException {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		Set<UserDTO> list = userService.getUsersByRoles(roles);
		verifyWorkers(list, roles);
	}

	private void verifyWorkers(Set<UserDTO> list, Set<UserRoleDTO> roles) {
		for (UserDTO u : list) {
			if (roles != null) {
				boolean contains = false;
				for (UserRoleDTO role : roles) {
					contains = contains || u.getAuthorities().contains(role);
				}
				Assert.isTrue(contains);
			}
			if (u instanceof TranslatorDTO)
				Assert.notNull(((TranslatorDTO) u).getTranslations());
		}
	}

	private void verifyClients(Set<UserDTO> list, Set<UserRoleDTO> roles) {
		for (UserDTO u : list) {
			if (roles != null) {
				boolean contains = false;
				for (UserRoleDTO role : roles) {
					contains = contains || u.getAuthorities().contains(role);
				}
				Assert.isTrue(contains);
			}
			if (u instanceof BusinessClientDTO) {
				BusinessClientDTO bc = ((BusinessClientDTO) u);
				Assert.notNull(bc.getAgents());
				Iterator<PersonalDataDTO> i = bc.getAgents().iterator();
				while (i.hasNext()) {
					PersonalDataDTO data = i.next();
					Assert.notNull(data.getEmail());
					Assert.notNull(data.getPhoneNumber());
				}
			}

		}
	}

}
