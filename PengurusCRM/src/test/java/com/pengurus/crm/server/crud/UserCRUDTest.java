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

import com.pengurus.crm.daos.UserDAO;
import com.pengurus.crm.entities.User;
import com.pengurus.crm.shared.dto.UserRoleDTO;

@ContextConfiguration(locations = { "../testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserCRUDTest {

    @Autowired
    private UserDAO userDAO;
    
    private Set<UserRoleDTO> userRoleDTO;
    
    @Before
    public void before(){
        userRoleDTO = new HashSet<UserRoleDTO>();
        userRoleDTO.add(UserRoleDTO.ROLE_USER);
        userRoleDTO.add(UserRoleDTO.ROLE_EXPERT);
    }
    
    @Test
    public void simpleCreateUser() {
        User user = new User(userRoleDTO, "Test", "password", "description");
        userDAO.create(user);
        Assert.assertNotNull(user.getId());
    }
    
    @Test
    public void simpleGetUser(){
        // prepare data
        User user = new User(userRoleDTO, "Test2", "password", "description");
        userDAO.create(user);
        
        // test
        User userFromDB = userDAO.read(user.getId());
        Assert.assertEquals("Test2", userFromDB.getUsername());
        Assert.assertEquals("description", userFromDB.getDescription());
        Assert.assertEquals("password", userFromDB.getPassword());
    }
    
    @Test(expected = UsernameNotFoundException.class)
    public void simpleUpdateUser(){
        // prepare data
        User user = new User(userRoleDTO, "Test3", "password", "description");
        userDAO.create(user);        
        
        //test
        User userFromDB = userDAO.read(user.getId());
        userFromDB.setDescription("new description");
        userFromDB.setUsername("new name");
        userDAO.update(userFromDB);
        
        User newUserFromDB = userDAO.findByUsername("new name");
        Assert.assertEquals("new description", newUserFromDB.getDescription());
        userDAO.findByUsername("Test3");
    }
    
    @Test (expected=UsernameNotFoundException.class)
    public void simpleDeleteUser(){
        // prepare data
        User user = new User(userRoleDTO, "Test4", "password", "description");
        userDAO.create(user);
        
        //test
        userDAO.delete(user);
        userDAO.findByUsername("Test4");
    }
    
    @Test (expected=DataAccessException.class)
    public void addUsersWithTheSameUsername(){
        User user = new User(userRoleDTO, "Test4", "password", "description");
        User user2 = new User(userRoleDTO, "Test4", "password", "description2");
        userDAO.create(user);
        userDAO.create(user2);
    }

}
