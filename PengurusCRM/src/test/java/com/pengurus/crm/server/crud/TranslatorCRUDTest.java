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

import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;
import com.pengurus.crm.entities.Translator;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TranslatorCRUDTest {

    @Autowired
    private TranslatorDAO tranaslatorDAO;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private PriceDAO priceDAO;
    @Autowired
    private CurrencyTypeDAO currencyTypeDAO;
    @Autowired
    private TranslationTypeDAO translationTypeDAO;
    @Autowired
    private TranslationDAO translationDAO;

    private Set<UserRoleDTO> userRoleDTO;
    private PersonalData personalData;
    private Set<Translation> translations;
    private static int change;

    @Before
    public void before() {
        userRoleDTO = new HashSet<UserRoleDTO>();
        userRoleDTO.add(UserRoleDTO.ROLE_USER);
        userRoleDTO.add(UserRoleDTO.ROLE_EXPERT);

        Set<String> phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");
        Set<String> emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
        personalData = new PersonalData("Firstame", "Lastname", "Address",
                phoneNumbers, emails);

        TranslationType translationType = new TranslationType("description",
                "word");
        translationTypeDAO.create(translationType);

        Language lto = new Language(("English" + Integer.toString(change)));
        Language lfrom = new Language(("Polish" + Integer.toString(change)));
        languageDAO.create(lfrom);
        languageDAO.create(lto);

        CurrencyType currencyType = new CurrencyType(
                ("PLN" + Integer.toString(change)));
        currencyTypeDAO.create(currencyType);

        Price price = new Price(100, currencyType);
        priceDAO.create(price);

        translations = new HashSet<Translation>();
        Translation translation = new Translation(translationType, lfrom, lto,
                price);
        translationDAO.create(translation);
        translations.add(translation);
        change++;
    }

    @Test
    public void simpleCreateTranslator() {
        Translator tranaslator = new Translator(userRoleDTO, "TranslatorTest",
                "password", "description", personalData, translations);
        tranaslatorDAO.create(tranaslator);
        Assert.assertNotNull(tranaslator.getId());
    }

    @Test
    public void simpleGetTranslator() {
        // prepare data
        Translator tranaslator = new Translator(userRoleDTO, "TranslatorTest2",
                "password", "description", personalData, translations);
        tranaslatorDAO.create(tranaslator);

        // test
        Translator tranaslatorFromDB = tranaslatorDAO.read(tranaslator.getId());
        Assert.assertEquals("TranslatorTest2", tranaslatorFromDB.getUsername());
        Assert.assertEquals("description", tranaslatorFromDB.getDescription());
        Assert.assertEquals("password", tranaslatorFromDB.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateTranslator() {
        // prepare data
        Translator tranaslator = new Translator(userRoleDTO, "TranslatorTest3",
                "password", "description", personalData, translations);
        tranaslatorDAO.create(tranaslator);

        // test
        Translator tranaslatorFromDB = tranaslatorDAO.read(tranaslator.getId());
        tranaslatorFromDB.setDescription("new description");
        tranaslatorFromDB.setUsername("new name1");
        tranaslatorDAO.update(tranaslatorFromDB);

        Translator newTranslatorFromDB = tranaslatorDAO
                .findByUsername("new name1");
        Assert.assertEquals("new description",
                newTranslatorFromDB.getDescription());
        tranaslatorDAO.findByUsername("TranslatorTest3");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleDeleteTranslator() {
        // prepare data
        Translator tranaslator = new Translator(userRoleDTO, "TranslatorTest4",
                "password", "description", personalData, translations);
        tranaslatorDAO.create(tranaslator);

        // test
        tranaslatorDAO.delete(tranaslator);
        tranaslatorDAO.findByUsername("TranslatorTest4");
    }

    @Test(expected = DataAccessException.class)
    public void addTranslatorsWithTheSameUsername() {
        Translator tranaslator = new Translator(userRoleDTO, "TranslatorTest4",
                "password", "description", personalData, translations);
        Translator tranaslator2 = new Translator(userRoleDTO,
                "TranslatorTest4", "password", "description2", personalData,
                translations);
        tranaslatorDAO.create(tranaslator);
        tranaslatorDAO.create(tranaslator2);
    }

}
