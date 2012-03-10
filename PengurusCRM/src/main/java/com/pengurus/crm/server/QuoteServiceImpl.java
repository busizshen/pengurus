package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.daos.ClientDAO;
import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.Quote;
import com.pengurus.crm.enums.Status;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuoteServiceImpl implements QuoteService {

	private QuoteDAO quoteDAO;
	private ClientDAO clientDAO;

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	protected static final Logger log = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Override
	public Set<QuoteDTO> getQuotes() {
		List<Quote> list = quoteDAO.loadAll();
		Set<QuoteDTO> set = new HashSet<QuoteDTO>();
		for (Quote q : list) {
			set.add(q.toDTOLazy());
		}
		return set;
	}

	public QuoteDAO getQuoteDAO() {
		return quoteDAO;
	}

	public void setQuoteDAO(QuoteDAO quoteDAO) {
		this.quoteDAO = quoteDAO;
	}

	@Override
	public void updateQuoteStatus(QuoteDTO quoteDTO) {
		Quote quote = this.quoteDAO.read(quoteDTO.getId());
		quote.setStatus(Status.toStatus(quoteDTO.getStatus()));
		this.quoteDAO.update(quote);
	}

	@Override
	public void updateQuote(QuoteDTO quoteDTO) {
		this.quoteDAO.update(new Quote(quoteDTO));
	}

	@Override
	public void deleteQuote(QuoteDTO quoteDTO) {
		this.quoteDAO.delete(new Quote(quoteDTO));
	}

	@Override
	public QuoteDTO createQuote(QuoteDTO quoteDTO) {
		quoteDTO.setId(quoteDAO.create(new Quote(quoteDTO)));
		return quoteDTO;
	}

	@Override
	public QuoteDTO getQuote(Long id) {
		return quoteDAO.read(id).toDTO();
	}

}
