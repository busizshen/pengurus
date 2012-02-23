package com.pengurus.crm.server.crud;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.entities.TranslationType;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TranslationTypeCRUDTest {

    @Autowired
    private TranslationTypeDAO translationTypeDAO;

    @Test
    public void simpleCreateTranslationType() {
        TranslationType translationType = new TranslationType("description",
                "word");
        translationTypeDAO.create(translationType);
        Assert.assertNotNull(translationType.getId());
    }

    @Test
    public void simpleGetTranslationType() {
        // prepare data
        TranslationType translationType = new TranslationType("description",
                "word");
        translationTypeDAO.create(translationType);

        // test
        TranslationType translationType2 = translationTypeDAO
                .read(translationType.getId());
        Assert.assertEquals(translationType.getId(), translationType2.getId());
        Assert.assertEquals(translationType.getDescription(),
                translationType2.getDescription());
        Assert.assertEquals(translationType.getUnit(),
                translationType2.getUnit());
    }

    @Test
    public void simpleUpdateTranslationType() {
        // prepare data
        TranslationType translationType = new TranslationType("description",
                "word");
        translationTypeDAO.create(translationType);

        // test
        TranslationType translationType2 = translationTypeDAO
                .read(translationType.getId());
        translationType2.setDescription("new description");
        translationType2.setUnit("new unit");
        translationTypeDAO.update(translationType2);
        TranslationType translationType3 = translationTypeDAO
                .read(translationType.getId());
        Assert.assertEquals(translationType3.getUnit(), "new unit");
        Assert.assertEquals(translationType3.getDescription(),
                "new description");
    }

    @Test
    public void simpleDeleteTranslationType() {
        // prepare data
        TranslationType translationType = new TranslationType("description",
                "unit");
        translationTypeDAO.create(translationType);

        // test
        translationTypeDAO.delete(translationType);
        Assert.assertNull(translationTypeDAO.read(translationType.getId()));
    }

}
