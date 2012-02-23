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
import com.pengurus.crm.daos.WorkerDAO;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Worker;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkerCRUDTest {

    @Autowired
    private WorkerDAO workerDAO;

    private Set<UserRoleDTO> workerRoleDTO;
    private PersonalData personalData;

    @Before
    public void before() {
        workerRoleDTO = new HashSet<UserRoleDTO>();
        workerRoleDTO.add(UserRoleDTO.ROLE_USER);
        workerRoleDTO.add(UserRoleDTO.ROLE_EXPERT);

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
    public void simpleCreateWorker() {
        Worker worker = new Worker(workerRoleDTO, "WorkerTest", "password",
                "description", personalData);
        workerDAO.create(worker);
        Assert.assertNotNull(worker.getId());
    }

    @Test
    public void simpleGetWorker() {
        // prepare data
        Worker worker = new Worker(workerRoleDTO, "WorkerTest2", "password",
                "description", personalData);
        workerDAO.create(worker);

        // test
        Worker workerFromDB = workerDAO.read(worker.getId());
        Assert.assertEquals("WorkerTest2", workerFromDB.getUsername());
        Assert.assertEquals("description", workerFromDB.getDescription());
        Assert.assertEquals("password", workerFromDB.getPassword());
    }

    // add expception
    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateWorker() {
        // prepare data
        Worker worker = new Worker(workerRoleDTO, "WorkerTest3", "password",
                "description", personalData);
        workerDAO.create(worker);

        // test
        Worker workerFromDB = workerDAO.read(worker.getId());
        workerFromDB.setDescription("new description");
        workerFromDB.setUsername("new name2");
        workerDAO.update(workerFromDB);

        Worker newWorkerFromDB = workerDAO.findByUsername("new name2");
        Assert.assertEquals("new description", newWorkerFromDB.getDescription());
        workerDAO.findByUsername("WorkerTest3");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void simpleDeleteWorker() {
        // prepare data
        Worker worker = new Worker(workerRoleDTO, "WorkerTest4", "password",
                "description", personalData);
        workerDAO.create(worker);

        // test
        workerDAO.delete(worker);
        workerDAO.findByUsername("WorkerTest4");
    }

    @Test(expected = DataAccessException.class)
    public void addWorkersWithTheSameUsername() {
        Worker worker = new Worker(workerRoleDTO, "WorkerTest4", "password",
                "description", personalData);
        Worker worker2 = new Worker(workerRoleDTO, "WorkerTest4", "password",
                "description2", personalData);
        workerDAO.create(worker);
        workerDAO.create(worker2);
    }

}
