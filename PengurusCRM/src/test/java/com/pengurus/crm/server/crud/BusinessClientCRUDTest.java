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

import com.pengurus.crm.daos.BusinessClientDAO;
import com.pengurus.crm.entities.BusinessClient;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessClientCRUDTest {
    @Autowired
    private BusinessClientDAO businessClientDAO;

    private Set<UserRoleDTO> businessClientRoleDTO;
    private Set<PersonalData> personalData;

    @Before
    public void before() {
        businessClientRoleDTO = new HashSet<UserRoleDTO>();
        businessClientRoleDTO.add(UserRoleDTO.ROLE_USER);
        businessClientRoleDTO.add(UserRoleDTO.ROLE_EXPERT);

        Set<String> phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");
        Set<String> emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
        personalData = new HashSet<PersonalData>();
        personalData.add(new PersonalData("Firstame", "Lastname", "Address",
                phoneNumbers, emails));
    }

    @Test
    public void simpleCreateBusinessClient() {
        BusinessClient businessClient = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest", "password",
                "description", "Company", personalData);
        businessClientDAO.create(businessClient);
        Assert.assertNotNull(businessClient.getId());
    }

    @Test
    public void simpleGetBusinessClient() {
        // prepare data
        BusinessClient businessClient = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest2", "password",
                "description", "Company", personalData);
        businessClientDAO.create(businessClient);

        // test
        BusinessClient businessClientFromDB = businessClientDAO
                .read(businessClient.getId());
        Assert.assertEquals("BusinessClientTest2",
                businessClientFromDB.getUsername());
        Assert.assertEquals("description",
                businessClientFromDB.getDescription());
        Assert.assertEquals("password", businessClientFromDB.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateBusinessClient() {
        // prepare data
        BusinessClient businessClient = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest3", "password",
                "description", "Company", personalData);
        businessClientDAO.create(businessClient);

        // test
        BusinessClient businessClientFromDB = businessClientDAO
                .read(businessClient.getId());
        businessClientFromDB.setDescription("new description");
        businessClientFromDB.setUsername("new name4");
        businessClientDAO.update(businessClientFromDB);

        BusinessClient newBusinessClientFromDB = businessClientDAO
                .findByUsername("new name4");
        Assert.assertEquals("new description",
                newBusinessClientFromDB.getDescription());
        businessClientDAO.findByUsername("BusinessClientTest3");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleDeleteBusinessClient() {
        // prepare data
        BusinessClient businessClient = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest4", "password",
                "description", "Company", personalData);
        businessClientDAO.create(businessClient);

        // test
        businessClientDAO.delete(businessClient);
        businessClientDAO.findByUsername("BusinessClientTest4");
    }

    @Test(expected = DataAccessException.class)
    public void addBusinessClientsWithTheSameUsername() {
        BusinessClient businessClient = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest4", "password",
                "description", "Company", personalData);
        BusinessClient businessClient2 = new BusinessClient(
                businessClientRoleDTO, "BusinessClientTest4", "password",
                "description2", "Company", personalData);
        businessClientDAO.create(businessClient);
        businessClientDAO.create(businessClient2);
    }

}
