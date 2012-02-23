package com.pengurus.crm.server.crud;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.PersonalDataDAO;
import com.pengurus.crm.entities.PersonalData;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonalDataCRUDTest {

    @Autowired
    private PersonalDataDAO personalDataDAO;
    private Set<String> phoneNumbers;
    private Set<String> emails;

    @Before
    public void before() {
        phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");

        emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
    }

    @Test
    public void simpleCreatePersonalData() {
        PersonalData personalData = new PersonalData("first name", "last name",
                "address", phoneNumbers, emails);
        personalDataDAO.create(personalData);
        Assert.assertNotNull(personalData.getId());
    }

    @Test
    public void simpleGetPersonalData() {
        // prepare data
        PersonalData personalData = new PersonalData("first name", "last name",
                "address", phoneNumbers, emails);
        personalDataDAO.create(personalData);

        // test
        PersonalData personalData2 = personalDataDAO.read(personalData.getId());
        Assert.assertEquals(personalData.getAddress(),
                personalData2.getAddress());
        Assert.assertEquals(personalData.getFirstName(),
                personalData2.getFirstName());
        Assert.assertEquals(personalData.getLastName(),
                personalData2.getLastName());
        Assert.assertEquals(
                personalData.getEmail().containsAll(personalData2.getEmail()),
                true);
        Assert.assertEquals(
                personalData.getPhoneNumber().containsAll(
                        personalData2.getPhoneNumber()), true);
    }

    @Test
    public void simpleUpdatePersonalData() {
        // prepare data
        PersonalData personalData = new PersonalData("first name", "last name",
                "address", phoneNumbers, emails);
        personalDataDAO.create(personalData);

        // test
        personalData.setAddress("new address");
        personalData.setFirstName("new first name");
        personalData.setLastName("new last name");
        personalData.getEmail().add("newemail@email.com");
        personalData.getPhoneNumber().add("000-111-111");
        personalDataDAO.update(personalData);

        PersonalData personalData2 = personalDataDAO.read(personalData.getId());
        Assert.assertEquals(personalData.getAddress(),
                personalData2.getAddress());
        Assert.assertEquals(personalData.getFirstName(),
                personalData2.getFirstName());
        Assert.assertEquals(personalData.getLastName(),
                personalData2.getLastName());
        Assert.assertEquals(
                personalData.getEmail().containsAll(personalData2.getEmail()),
                true);
        Assert.assertEquals(
                personalData.getPhoneNumber().containsAll(
                        personalData2.getPhoneNumber()), true);
    }
    
    @Test
    public void simpleDeletePersonalData(){
        // prepare data
        PersonalData personalData = new PersonalData("first name", "last name",
                "address", phoneNumbers, emails);
        personalDataDAO.create(personalData);
        
        // test
        personalDataDAO.delete(personalData);
        Assert.assertNull(personalDataDAO.read(personalData.getId()));
        
    }

}
