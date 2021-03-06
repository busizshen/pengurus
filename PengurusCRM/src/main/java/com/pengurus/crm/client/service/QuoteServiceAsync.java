package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.QuoteDTO;

public interface QuoteServiceAsync {
	
	public void updateQuoteStatus(QuoteDTO quote, AsyncCallback<Void> callback);
	public void updateQuote(QuoteDTO quoteDTO, AsyncCallback<Void> callback);
	public void deleteQuote(QuoteDTO quoteDTO, AsyncCallback<Void> callback);
	
	public void createQuote(QuoteDTO quoteDTO, AsyncCallback<QuoteDTO> callback);
	public void getQuote(Long id, AsyncCallback<QuoteDTO> callback);
	
	public void getAllQuotes(AsyncCallback<Set<QuoteDTO>> callback);
	public void getQuotesBySupervisorId(Long id, AsyncCallback<Set<QuoteDTO>> callback);
	public void getQuotesByClientId(Long id, AsyncCallback<Set<QuoteDTO>> callback);
	
}
