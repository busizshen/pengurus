package com.pengurus.crm.server;

import org.hibernate.Session;
import org.junit.Test;

import com.pengurus.crm.hibernate.HibernateUtil;

public class SimpleTest {

	@Test
	public void test() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(new Kupa(5));
		session.getTransaction().commit();
	}

}
