package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

public interface QuoteServiceAsync {
	
	public void updateQuoteStatus(QuoteDTO quote, AsyncCallback<Void> callback);
	public void updateQuote(QuoteDTO quoteDTO, AsyncCallback<Void> callback);
	public void deleteQuote(QuoteDTO quoteDTO, AsyncCallback<Void> callback);
	
	public void createQuote(QuoteDTO quoteDTO, AsyncCallback<QuoteDTO> callback);
	public void getQuote(Long id, AsyncCallback<QuoteDTO> callback);
	
	public void getAllQuotes(AsyncCallback<Set<QuoteDTO>> callback);
	public void getQuotesBySupervisorId(Long id, AsyncCallback<Set<QuoteDTO>> callback);
	public void getQuotesByClientId(Long id, AsyncCallback<Set<QuoteDTO>> callback);

	public void getPaginatedAllQuotes(PagingLoadConfigHelper loadConfig,
			AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	public void getPaginatedQuotesBySupervisorId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	public void getPaginatedQuotesByClientId(PagingLoadConfigHelper loadConfig,
			Long id, AsyncCallback<PagingLoadResultHelper<QuoteModel>> callback);
	
}
