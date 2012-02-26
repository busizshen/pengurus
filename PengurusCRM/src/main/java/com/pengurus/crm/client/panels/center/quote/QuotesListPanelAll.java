package com.pengurus.crm.client.panels.center.quote;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotesListPanelAll extends QuotesListPanel {


	
	@Override
	protected ListStore<QuoteModel> getList() {
		final ListStore<QuoteModel> list = new ListStore<QuoteModel>();
		AsyncCallback<Set<QuoteDTO> > callback = new AsyncCallback<Set<QuoteDTO> >() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<QuoteDTO> result) {
				for(QuoteDTO q: result){
					list.add(new QuoteModel(q));
				}
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.getQuotes(callback);

		return list;
		}
	@Override
	protected String getName() {
		return "Quote List - All";
	}

}
