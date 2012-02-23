package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pengurus.crm.daos.BussinessClientDAO;
import com.pengurus.crm.daos.PersonalDataDAO;
import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.BussinessClient;
import com.pengurus.crm.entities.PersonalData;
import com.pengurus.crm.entities.Quote;

@ContextConfiguration(locations = { "testContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class QuoteDAOTest {

	@Autowired
	private QuoteDAO quoteDAO;
	
	@Autowired
	private BussinessClientDAO bussinessClientDAO;
	@Autowired
	private PersonalDataDAO personalDataDAO;
	
	@Before
	public void createQuotes(){
		Quote q1 = new Quote();
		q1.setDescription("one");
		quoteDAO.create(q1);
		BussinessClient bc = new BussinessClient();
		bc.setDescription("Bussiness Client");
		bc.setUsername("BC first");
		Set<PersonalData> agents = new HashSet<PersonalData>();
		PersonalData pd = new PersonalData("Name","Last Name", "Address", null,null);
		personalDataDAO.create(pd);
		agents.add(pd);
		bc.setAgents(agents );
		bussinessClientDAO.create(bc);
		Quote q2 = new Quote();
		q2.setDescription("two");
		q2.setClient(bc);
		quoteDAO.create(q2);
	}
	
	@Test
	public void empty() {
	}
	
	@Test
	public void load(){
		List<Quote> list = quoteDAO.loadAll();
		Assert.assertEquals(list.size(), 2);
	}
}
