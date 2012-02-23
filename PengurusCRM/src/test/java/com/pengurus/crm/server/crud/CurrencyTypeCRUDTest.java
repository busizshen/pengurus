package com.pengurus.crm.server.crud;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.entities.CurrencyType;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyTypeCRUDTest {

    @Autowired
    private CurrencyTypeDAO currencyTypeDAO;

    @Test
    public void simpleCreateCurrencyType() {
        CurrencyType currencyType = new CurrencyType("PLN");
        currencyTypeDAO.create(currencyType);
        Assert.assertNotNull(currencyType.getId());
    }

    @Test
    public void simpleGetCurrencyType() {
        // prepare data
        CurrencyType currencyType = new CurrencyType("EUR");
        currencyTypeDAO.create(currencyType);

        // test
        CurrencyType currencyType2 = currencyTypeDAO.read(currencyType.getId());
        Assert.assertEquals(currencyType.getId(), currencyType2.getId());
        Assert.assertEquals(currencyType.getCurrency(), currencyType2.getCurrency());
    }

    @Test
    public void simpleUpdateCurrencyType() {
        // prepare data
        CurrencyType currencyType = new CurrencyType("USD");
        currencyTypeDAO.create(currencyType);

        // test
        CurrencyType currencyType2 = currencyTypeDAO.read(currencyType.getId());
        currencyType2.setCurrency("CHF");
        currencyTypeDAO.update(currencyType2);
        CurrencyType currencyType3 = currencyTypeDAO.read(currencyType.getId());
        Assert.assertEquals(currencyType3.getCurrency(), "CHF");
    }

    @Test
    public void simpleDeleteCurrencyType() {
        // prepare data
        CurrencyType currencyType = new CurrencyType("CZK");
        currencyTypeDAO.create(currencyType);

        // test
        currencyTypeDAO.delete(currencyType);
        Assert.assertNull(currencyTypeDAO.read(currencyType.getId()));
    }

    @Test(expected = DataAccessException.class)
    public void addingTheSameCurrencyTypes() {
        CurrencyType currencyType = new CurrencyType("GBP");
        CurrencyType currencyType2 = new CurrencyType("GBP");
        currencyTypeDAO.create(currencyType);
        currencyTypeDAO.create(currencyType2);
    }

}
