package com.pengurus.crm.server.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.daos.ClientDAO;
import com.pengurus.crm.daos.QuoteDAO;
import com.pengurus.crm.entities.Quote;
import com.pengurus.crm.enums.StatusQuote;
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

	public QuoteDAO getQuoteDAO() {
		return quoteDAO;
	}

	public void setQuoteDAO(QuoteDAO quoteDAO) {
		this.quoteDAO = quoteDAO;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_ACCOUNTANT')")
	public Set<QuoteDTO> getAllQuotes() {
		List<Quote> list = quoteDAO.loadAll();
		Set<QuoteDTO> set = new HashSet<QuoteDTO>();
		for (Quote q : list) {
			set.add(q.toDTOLazy());
		}
		return set;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_ACCOUNTANT') or hasPermission(#quoteDTO, 'write')")
	public void updateQuoteStatus(QuoteDTO quoteDTO) {
		Quote quote = this.quoteDAO.read(quoteDTO.getId());
		quote.setStatus(StatusQuote.toStatus(quoteDTO.getStatus()));
		this.quoteDAO.update(quote);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public void updateQuote(QuoteDTO quoteDTO) {
		this.quoteDAO.update(new Quote(quoteDTO));
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public void deleteQuote(QuoteDTO quoteDTO) {
		this.quoteDAO.delete(new Quote(quoteDTO));
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public QuoteDTO createQuote(QuoteDTO quoteDTO) {
		quoteDTO.setId(quoteDAO.create(new Quote(quoteDTO)));
		return quoteDTO;
	}

	@Override
	@PostAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_ACCOUNTANT') or hasPermission(returnObject, 'write')")
	public QuoteDTO getQuote(Long id) {
		return quoteDAO.getById(id).toDTO();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_EXECUTIVE')")
	public Set<QuoteDTO> getQuotesBySupervisorId(Long id) {
		List<Quote> list = quoteDAO.loadAllBySupervisorId(id);
		Set<QuoteDTO> set = new HashSet<QuoteDTO>();
		for (Quote q : list) {
			set.add(q.toDTOLazy());
		}
		return set;
	}

	@Override
	@PreAuthorize("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_ACCOUNTANT', 'ROLE_CLIENT')")
	@PostFilter("hasAnyRole('ROLE_EXECUTIVE', 'ROLE_ACCOUNTANT') or hasPermission(filterObject, 'read')")
	public Set<QuoteDTO> getQuotesByClientId(Long id) {
		List<Quote> list = quoteDAO.loadAllByClientId(id);
		Set<QuoteDTO> set = new HashSet<QuoteDTO>();
		for (Quote q : list) {
			set.add(q.toDTOLazy());
		}
		return set;
	}
}
