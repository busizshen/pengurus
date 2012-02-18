package com.pengurus.crm.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.dto.QuoteDTO;

public interface QuoteServiceAsync {
	public void getQuotes(AsyncCallback<Set<QuoteDTO>> callback);
}
