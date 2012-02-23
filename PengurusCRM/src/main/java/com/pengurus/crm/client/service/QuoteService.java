package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.shared.dto.QuoteDTO;

@RemoteServiceRelativePath("quote.rpc")
public interface QuoteService extends RemoteService {
	public Set<QuoteDTO> getQuotes();
	public void updateQuoteStatus(QuoteDTO quote);
	public void updateQuote(QuoteDTO quoteDTO);
}
