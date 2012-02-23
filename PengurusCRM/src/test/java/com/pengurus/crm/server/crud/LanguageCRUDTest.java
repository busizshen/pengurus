package com.pengurus.crm.server.crud;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.entities.Language;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class LanguageCRUDTest {

    @Autowired
    private LanguageDAO languageDAO;
    
    @Test
    public void simpleCreateLanguage() {
        Language language = new Language("Polish");
        languageDAO.create(language);
        Assert.assertNotNull(language.getId());
    }

    @Test
    public void simpleGetLanguage() {
        // prepare data
        Language language = new Language("English");
        languageDAO.create(language);

        // test
        Language language2 = languageDAO.read(language.getId());
        Assert.assertEquals(language.getId(), language2.getId());
        Assert.assertEquals(language.getLanguage(), language2.getLanguage());
    }

    @Test
    public void simpleUpdateLanguage() {
        // prepare data
        Language language = new Language("Spanish");
        languageDAO.create(language);

        // test
        Language language2 = languageDAO.read(language.getId());
        language2.setLanguage("German");
        languageDAO.update(language2);
        Language language3 = languageDAO.read(language.getId());
        Assert.assertEquals(language3.getLanguage(), "German");
    }

    @Test
    public void simpleDeleteLanguage() {
        // prepare data
        Language language = new Language("Spanish");
        languageDAO.create(language);

        // test
        languageDAO.delete(language);
        Assert.assertNull(languageDAO.read(language.getId()));
    }

    @Test(expected = DataAccessException.class)
    public void addingTheSameLanguages() {
        Language language = new Language("Italian");
        Language language2 = new Language("Italian");
        languageDAO.create(language);
        languageDAO.create(language2);
    }

}
