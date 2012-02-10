package com.pengurus.crm.daos.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.pengurus.crm.daos.GenericDAO;


public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T>{
	
	protected Class<T> type;
	
	@Override
	public long create(T newInstance) {
		try{
			return (Long) getHibernateTemplate().save(newInstance);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return Long.parseLong("-1");
		}
	}

	@Override
	public T read(long id) {
		try{
			return getHibernateTemplate().get(type, id);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean update(T transientObject) {
		try {
			getHibernateTemplate().update(transientObject);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(T persistentObject) {
		try{
			getHibernateTemplate().delete(persistentObject);
			return true;
		} catch(Exception e){
			return false;
		}
		
	}

}
