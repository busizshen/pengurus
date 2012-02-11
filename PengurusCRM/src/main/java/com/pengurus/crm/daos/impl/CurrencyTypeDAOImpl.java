package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.entities.CurrencyType;

public class CurrencyTypeDAOImpl extends GenericDAOImpl<CurrencyType> implements
        CurrencyTypeDAO {

    protected static final Logger log = LoggerFactory
            .getLogger(CurrencyTypeDAOImpl.class);

    public CurrencyTypeDAOImpl() {
        this.type = CurrencyType.class;
    }

}