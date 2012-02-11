package com.pengurus.crm.daos.impl;

import com.pengurus.crm.daos.TranslationTypeDAO;
import com.pengurus.crm.entities.TranslationType;

public class TranslationTypeDAOImpl extends GenericDAOImpl<TranslationType> implements TranslationTypeDAO {

    public TranslationTypeDAOImpl(){
        this.type = TranslationType.class;
    }
}
