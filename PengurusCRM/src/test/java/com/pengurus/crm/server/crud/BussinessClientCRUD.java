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
import com.pengurus.crm.daos.BussinessClientDAO;
import com.pengurus.crm.entities.BussinessClient;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BussinessClientCRUD {
    @Autowired
    private BussinessClientDAO bussinessClientDAO;

    private Set<UserRoleDTO> bussinessClientRoleDTO;
    private Set<PersonalData> personalData;

    @Before
    public void before() {
        bussinessClientRoleDTO = new HashSet<UserRoleDTO>();
        bussinessClientRoleDTO.add(UserRoleDTO.ROLE_USER);
        bussinessClientRoleDTO.add(UserRoleDTO.ROLE_EXPERT);

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
    public void simpleCreateBussinessClient() {
        BussinessClient bussinessClient = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest", "password",
                "description", "Company", personalData);
        bussinessClientDAO.create(bussinessClient);
        Assert.assertNotNull(bussinessClient.getId());
    }

    @Test
    public void simpleGetBussinessClient() {
        // prepare data
        BussinessClient bussinessClient = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest2", "password",
                "description", "Company", personalData);
        bussinessClientDAO.create(bussinessClient);

        // test
        BussinessClient bussinessClientFromDB = bussinessClientDAO
                .read(bussinessClient.getId());
        Assert.assertEquals("BussinessClientTest2",
                bussinessClientFromDB.getUsername());
        Assert.assertEquals("description",
                bussinessClientFromDB.getDescription());
        Assert.assertEquals("password", bussinessClientFromDB.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateBussinessClient() {
        // prepare data
        BussinessClient bussinessClient = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest3", "password",
                "description", "Company", personalData);
        bussinessClientDAO.create(bussinessClient);

        // test
        BussinessClient bussinessClientFromDB = bussinessClientDAO
                .read(bussinessClient.getId());
        bussinessClientFromDB.setDescription("new description");
        bussinessClientFromDB.setUsername("new name4");
        bussinessClientDAO.update(bussinessClientFromDB);

        BussinessClient newBussinessClientFromDB = bussinessClientDAO
                .findByUsername("new name4");
        Assert.assertEquals("new description",
                newBussinessClientFromDB.getDescription());
        bussinessClientDAO.findByUsername("BussinessClientTest3");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleDeleteBussinessClient() {
        // prepare data
        BussinessClient bussinessClient = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest4", "password",
                "description", "Company", personalData);
        bussinessClientDAO.create(bussinessClient);

        // test
        bussinessClientDAO.delete(bussinessClient);
        bussinessClientDAO.findByUsername("BussinessClientTest4");
    }

    @Test(expected = DataAccessException.class)
    public void addBussinessClientsWithTheSameUsername() {
        BussinessClient bussinessClient = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest4", "password",
                "description", "Company", personalData);
        BussinessClient bussinessClient2 = new BussinessClient(
                bussinessClientRoleDTO, "BussinessClientTest4", "password",
                "description2", "Company", personalData);
        bussinessClientDAO.create(bussinessClient);
        bussinessClientDAO.create(bussinessClient2);
    }

}
