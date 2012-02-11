package com.pengurus.crm.daos;

import com.pengurus.crm.entities.Translator;

public interface TranslatorDAO extends GenericDAO<Translator>{
	
	public Translator findByUsername(String username);

}
