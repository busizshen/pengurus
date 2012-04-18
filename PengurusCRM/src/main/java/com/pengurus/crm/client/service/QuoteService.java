package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;
import com.pengurus.crm.shared.pagination.PagingLoadResultHelper;

@RemoteServiceRelativePath("quote.rpc")
public interface QuoteService extends RemoteService {
	
	public void updateQuoteStatus(QuoteDTO quote);
	public void updateQuote(QuoteDTO quoteDTO);
	public void deleteQuote(QuoteDTO quoteDTO);
	
	public QuoteDTO createQuote(QuoteDTO quoteDTO);
	public QuoteDTO getQuote(Long id);
	
	public Set<QuoteDTO> getAllQuotes();
	public Set<QuoteDTO> getQuotesBySupervisorId(Long id);
	public Set<QuoteDTO> getQuotesByClientId(Long id);
	
	public PagingLoadResultHelper<QuoteModel> getPaginatedAllQuotes(PagingLoadConfigHelper loadConfig);
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesBySupervisorId(PagingLoadConfigHelper loadConfig, Long id);
	public PagingLoadResultHelper<QuoteModel> getPaginatedQuotesByClientId(PagingLoadConfigHelper loadConfig, Long id);

}
