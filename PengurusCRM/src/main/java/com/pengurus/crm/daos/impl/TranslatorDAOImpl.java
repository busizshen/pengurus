package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pengurus.crm.daos.TranslatorDAO;
import com.pengurus.crm.entities.Translator;

public class TranslatorDAOImpl extends GenericDAOImpl<Translator> implements TranslatorDAO {
	
	protected static final Logger log = LoggerFactory.getLogger(TranslatorDAOImpl.class);
	
	public TranslatorDAOImpl(){
		this.type = Translator.class;
	}

	@Override
	public Translator findByUsername(String username) {
		try{
			String query = "select c from Translator c where c.username = '" + username + "'";
			return (Translator) getHibernateTemplate().find(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new UsernameNotFoundException(username);
		}
	}

}
