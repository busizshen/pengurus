package com.pengurus.crm.server.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.entities.Price;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PriceCRUDTest {

    @Autowired
    private PriceDAO priceDAO;
    @Autowired
    private CurrencyTypeDAO currencyTypeDAO;
    private Long currencyTypeId;
    private static int change = 0;

    @Before
    public void before() {
        CurrencyType currencyType = new CurrencyType("Portuguese"
                + Integer.toString(change));
        currencyTypeDAO.create(currencyType);
        currencyTypeId = currencyType.getId();
        change++;
    }

    @Test
    public void simpleCreatePrice() {
        Price price = new Price(200, currencyTypeDAO.read(currencyTypeId));
        priceDAO.create(price);
        Assert.assertNotNull(price.getId());
    }

    @Test
    public void simpleGetPrice() {
        // prepare data
        Price price = new Price(200, currencyTypeDAO.read(currencyTypeId));
        priceDAO.create(price);

        // test
        Price price2 = priceDAO.read(price.getId());
        Assert.assertEquals(price.getId(), price2.getId());
        Assert.assertEquals(price.getPrice(), price2.getPrice());
        Assert.assertEquals(price.getCurrency().getCurrency(), price2
                .getCurrency().getCurrency());
    }

    @Test
    public void simpleUpdatePrice() {
        // prepare data
        Price price = new Price(200, currencyTypeDAO.read(currencyTypeId));
        priceDAO.create(price);

        // test
        Price price2 = priceDAO.read(price.getId());
        CurrencyType currencyType = new CurrencyType("JPY");
        price2.setPrice(1000);
        price2.setCurrency(currencyType);
        priceDAO.update(price2);
        Price price3 = priceDAO.read(price.getId());
        Assert.assertEquals(price3.getPrice(), new Integer(1000));
        Assert.assertEquals(price3.getCurrency().getCurrency(),
                currencyType.getCurrency());
    }

    @Test
    public void simpleDeletePrice() {
        // prepare data
        Price price = new Price(200, currencyTypeDAO.read(currencyTypeId));
        priceDAO.create(price);

        // test
        priceDAO.delete(price);
        Assert.assertNull(priceDAO.read(price.getId()));
    }

}
