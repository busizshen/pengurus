package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.TranslationDAO;
import com.pengurus.crm.entities.Translation;

public class TranslationDAOImpl extends GenericDAOImpl<Translation> implements TranslationDAO {
    
protected static final Logger log = LoggerFactory.getLogger(TranslationDAOImpl.class);
    
    public TranslationDAOImpl(){
        this.type = Translation.class;
    }

}
