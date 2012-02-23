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

import com.pengurus.crm.daos.IndividualClientDAO;
import com.pengurus.crm.entities.IndividualClient;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class IndividualClientCRUDTest {

    @Autowired
    private IndividualClientDAO individualClientDAO;

    private Set<UserRoleDTO> individualClientRoleDTO;
    private PersonalData personalData;

    @Before
    public void before() {
        individualClientRoleDTO = new HashSet<UserRoleDTO>();
        individualClientRoleDTO.add(UserRoleDTO.ROLE_USER);
        individualClientRoleDTO.add(UserRoleDTO.ROLE_EXPERT);

        Set<String> phoneNumbers = new HashSet<String>();
        phoneNumbers.add("00-000-000");
        phoneNumbers.add("00-001-001");
        Set<String> emails = new HashSet<String>();
        emails.add("john@yahoo.com");
        emails.add("john2@yahoo.com");
        personalData = new PersonalData("Firstame", "Lastname", "Address",
                phoneNumbers, emails);
    }

    @Test
    public void simpleCreateIndividualClient() {
        IndividualClient individualClient = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest", "password",
                "description", personalData);
        individualClientDAO.create(individualClient);
        Assert.assertNotNull(individualClient.getId());
    }

    @Test
    public void simpleGetIndividualClient() {
        // prepare data
        IndividualClient individualClient = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest2", "password",
                "description", personalData);
        individualClientDAO.create(individualClient);

        // test
        IndividualClient individualClientFromDB = individualClientDAO
                .read(individualClient.getId());
        Assert.assertEquals("IndividualClientTest2",
                individualClientFromDB.getUsername());
        Assert.assertEquals("description",
                individualClientFromDB.getDescription());
        Assert.assertEquals("password", individualClientFromDB.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateIndividualClient() {
        // prepare data
        IndividualClient individualClient = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest3", "password",
                "description", personalData);
        individualClientDAO.create(individualClient);

        // test
        IndividualClient individualClientFromDB = individualClientDAO
                .read(individualClient.getId());
        individualClientFromDB.setDescription("new description");
        individualClientFromDB.setUsername("new name4");
        individualClientDAO.update(individualClientFromDB);

        IndividualClient newIndividualClientFromDB = individualClientDAO
                .findByUsername("new name4");
        Assert.assertEquals("new description",
                newIndividualClientFromDB.getDescription());
        individualClientDAO.findByUsername("IndividualClientTest3");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleDeleteIndividualClient() {
        // prepare data
        IndividualClient individualClient = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest4", "password",
                "description", personalData);
        individualClientDAO.create(individualClient);

        // test
        individualClientDAO.delete(individualClient);
        individualClientDAO.findByUsername("IndividualClientTest4");
    }

    @Test(expected = DataAccessException.class)
    public void addIndividualClientsWithTheSameUsername() {
        IndividualClient individualClient = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest4", "password",
                "description", personalData);
        IndividualClient individualClient2 = new IndividualClient(
                individualClientRoleDTO, "IndividualClientTest4", "password",
                "description2", personalData);
        individualClientDAO.create(individualClient);
        individualClientDAO.create(individualClient2);
    }

}
