package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.PersonalDataDAO;
import com.pengurus.crm.entities.PersonalData;

public class PersonalDataDAOImpl extends GenericDAOImpl<PersonalData> implements
        PersonalDataDAO {

    protected static final Logger log = LoggerFactory
            .getLogger(PersonalDataDAOImpl.class);

    public PersonalDataDAOImpl() {
        this.type = PersonalData.class;
    }

}