package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.Quote;

public class QuoteDAOImpl extends GenericDAOImpl<Quote> implements
        QuoteDAO {

    protected static final Logger log = LoggerFactory
            .getLogger(QuoteDAOImpl.class);

    public QuoteDAOImpl() {
        this.type = Quote.class;
    }

}