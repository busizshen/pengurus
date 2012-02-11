package com.pengurus.crm.daos.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.daos.PriceDAO;
import com.pengurus.crm.entities.Price;

public class PriceDAOImpl extends GenericDAOImpl<Price> implements PriceDAO {
    
    protected static final Logger log = LoggerFactory.getLogger(PriceDAOImpl.class);
    
    public PriceDAOImpl(){
        this.type = Price.class;
    }

}
