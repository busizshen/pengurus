package com.pengurus.crm.daos;

import com.pengurus.crm.entities.Quote;

public interface QuoteDAO extends GenericDAO<Quote>{
    
    Quote read(long id);
    
}