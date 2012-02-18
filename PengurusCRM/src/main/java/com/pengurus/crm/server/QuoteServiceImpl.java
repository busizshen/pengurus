package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.Quote;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuoteServiceImpl implements QuoteService {

	private QuoteDAO quoteDAO;
	
	protected static final Logger log = LoggerFactory
	.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	public Set<QuoteDTO> getQuotes() {
		List<Quote> list = quoteDAO.loadAll();
		Set<QuoteDTO> set = new HashSet<QuoteDTO>();
		for(Quote q : list){
			set.add(q.toQuoteDTO());
		}
		return set;
	}
	
	public QuoteDAO getQuoteDAO() {
		return quoteDAO;
	}
	public void setQuoteDAO(QuoteDAO quoteDAO) {
		this.quoteDAO = quoteDAO;
	}

}
