package com.pengurus.crm.server.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.Translation;
import com.pengurus.crm.entities.TranslationType;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TranslationCRUDTest {

    @Autowired
    private TranslationDAO translationDAO;
    @Autowired
    private LanguageDAO languageDAO;
    @Autowired
    private TranslationTypeDAO translationTypeDAO;
    @Autowired
    private PriceDAO PriceDAO;

    private Language to;
    private Language from;
    private TranslationType translationType;
    private Price price;
    private static int change = 0;

    @Before
    public void before() {
        to = new Language("LanguageTranslationTo" + Integer.toString(change));
        from = new Language("LanguageTranslationFrom" + Integer.toString(change));
        languageDAO.create(from);
        languageDAO.create(to);

        translationType = new TranslationType("description", "unit");
        translationTypeDAO.create(translationType);

        price = new Price(200 + change, new CurrencyType("$$$"
                + Integer.toString(change)));
        PriceDAO.create(price);
        change++;
    }

    @Test
    public void simpleCreateTranslation() {
        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);
        Assert.assertNotNull(translation.getId());
    }

    @Test
    public void simpleGetTranslation() {
        // prepare data
        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);

        // test
        Translation translation2 = translationDAO.read(translation.getId());
        Assert.assertEquals(translation2.getLfrom().getLanguage(), translation
                .getLfrom().getLanguage());
        Assert.assertEquals(translation2.getLto().getLanguage(), translation
                .getLto().getLanguage());
        Assert.assertEquals(translation2.getType().getDescription(),
                translation.getType().getDescription());
        Assert.assertEquals(translation2.getType().getUnit(), translation
                .getType().getUnit());
        Assert.assertEquals(translation2.getDefaultPrice().getPrice(),
                translation.getDefaultPrice().getPrice());
        Assert.assertEquals(translation2.getDefaultPrice().getCurrency()
                .getCurrency(), translation.getDefaultPrice().getCurrency()
                .getCurrency());
    }

    @Test
    public void simpleUpdateTranslation() {
        // prepare data
        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);
        Language language1 = new Language("language1");
        Language language2 = new Language("language2");
        languageDAO.create(language1);
        languageDAO.create(language2);

        // test
        translation.setLfrom(language2);
        translation.setLto(language1);
        translationDAO.update(translation);

        translation.getType().setDescription("translation descritpion");
        translationTypeDAO.update(translation.getType());

        Translation translation2 = translationDAO.read(translation.getId());
        Assert.assertEquals(translation2.getLfrom().getLanguage(),
                language2.getLanguage());
        Assert.assertEquals(translation2.getLto().getLanguage(),
                language1.getLanguage());
        Assert.assertEquals(translation.getType().getDescription(),
                "translation descritpion");
    }
    
    @Test
    public void simpleDeleteTranslation(){
        // prepare data
        Translation translation = new Translation(translationType, from, to,
                price);
        translationDAO.create(translation);
        
        // test
        translationDAO.delete(translation);
        Assert.assertNull(translationDAO.read(translation.getId()));
    }

}
