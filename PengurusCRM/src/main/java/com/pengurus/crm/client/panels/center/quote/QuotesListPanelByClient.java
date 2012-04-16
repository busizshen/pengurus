package com.pengurus.crm.client.panels.center.quote;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public class QuotesListPanelByClient extends QuotesListPanelByUser {

	public QuotesListPanelByClient(UserDTO userDTO) {
		super(userDTO);
	}

	public QuotesListPanelByClient(UserDTO userDTO, int height, int width) {
		super(userDTO, height, width);
	}

	@Override
	protected void changeService(AsyncCallback<Set<QuoteDTO>> callback) {
		QuoteServiceAsync quoteService = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		quoteService.getQuoteByClientId(userDTO.getId(), callback);
	}

}
