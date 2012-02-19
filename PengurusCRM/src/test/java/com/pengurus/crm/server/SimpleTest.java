package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.PersonalDataDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.daos.WorkerDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleTest {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TranslationTypeDAO translationTypeDAO;

	@Autowired
	private CurrencyTypeDAO currencyTypeDAO;

	@Autowired
	private PriceDAO priceDAO;

	@Autowired
	private TranslationDAO translationDAO;

	@Autowired
	private LanguageDAO languageDAO;

	@Autowired
	private AuthorizationService authService;

	@Autowired
	private WorkerDAO workerDAO;

	@Autowired
	private TranslatorDAO translatorDAO;

	@Autowired
	private PersonalDataDAO personalDataDAO;

	@Autowired
	private UserService userService;

	@Before
	public void createUser() {
		HashSet<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
		UserRoleDTO userRole = UserRoleDTO.ROLE_USER;
		userRoles.add(userRole);

		User user = new User(userRoles, "Username", "pass", "descr");
		UserDTO userDTO = user.toUserDTO();
		userService.createUser(userDTO);	
	}
	
	@After
	public void deleteUser(){
	    userDAO.delete(userDAO.findByUsername("Username"));
	    
	}

	@Test
	public void empty() {

	}

	@Test
	public void createTranlsation() {

		TranslationType translationType = new TranslationType("test", "test");
		translationTypeDAO.create(translationType);

		CurrencyType currencyType = new CurrencyType("Pound");
		Price price = new Price(100, currencyType);
		currencyTypeDAO.create(currencyType);

		priceDAO.create(price);

		price = new Price(200, currencyType);
		priceDAO.create(price);

		Language lfrom = new Language("English");
		languageDAO.create(lfrom);

		Language lto = new Language("Spanish");
		languageDAO.create(lto);

		Translation translation = new Translation(translationType, lfrom, lto,
				price);
		translationDAO.create(translation);

	}

	@Test
	public void workerTest() {
		Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
		UserRoleDTO userRole = UserRoleDTO.ROLE_USER;
		userRoles.add(userRole);
		PersonalData personalData = new PersonalData("John", "Doe", "address",
				new HashSet<String>(), new HashSet<String>());
		personalDataDAO.create(personalData);
		Worker worker = new Worker(userRoles, "worker", "pass", "descr",
				personalData);
		workerDAO.create(worker);
	}

	@Test
	public void translatorTest() {
		TranslationType translationType = new TranslationType("test", "test");
		translationTypeDAO.create(translationType);
		CurrencyType currencyType = new CurrencyType("Pound2");
		Price price = new Price(100, currencyType);
		currencyTypeDAO.create(currencyType);

		priceDAO.create(price);

		price = new Price(200, currencyType);
		priceDAO.create(price);

		Language lfrom = new Language("English2");
		languageDAO.create(lfrom);

		Language lto = new Language("Spanish2");
		languageDAO.create(lto);

		Translation translation = new Translation(translationType, lfrom, lto,
				price);
		translationDAO.create(translation);

		Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
		UserRoleDTO userRole = UserRoleDTO.ROLE_USER;
		userRoles.add(userRole);

		Set<Translation> translations = new HashSet<Translation>();
		translations.add(translation);

		PersonalData personalData = new PersonalData("John", "Doe", "address",
				new HashSet<String>(), new HashSet<String>());
		personalDataDAO.create(personalData);
		Translator translator = new Translator(userRoles, "translator", "pass",
				"descr", personalData, translations);
		translatorDAO.create(translator);

	}

	@Test
	public void logIn() {
		// trzeba encodowac haslo
		authService.login("Username", "pass");
		Assert.assertTrue(SecurityContextHolder.getContext()
				.getAuthentication().isAuthenticated());
	}

	@Test(expected = BadCredentialsException.class)
	public void logInNoUser() {
		authService.login("Username3", "pass");
	}

}
