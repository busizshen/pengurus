package com.pengurus.crm.daos.impl;

import com.pengurus.crm.daos.LanguageDAO;
import com.pengurus.crm.entities.Language;

public class LanguageDAOImpl extends GenericDAOImpl<Language> implements LanguageDAO {

    public LanguageDAOImpl(){
        this.type = Language.class;
    }

}
