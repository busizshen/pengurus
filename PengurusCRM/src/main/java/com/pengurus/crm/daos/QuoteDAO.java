package com.pengurus.crm.daos;

import java.util.List;

import com.pengurus.crm.entities.Quote;

public interface QuoteDAO extends GenericDAO<Quote>{
    
    Quote getById(long id);
	List<Quote> loadAllBySupervisorId(Long id);
	List<Quote> loadAllByClientId(Long id);
	
}