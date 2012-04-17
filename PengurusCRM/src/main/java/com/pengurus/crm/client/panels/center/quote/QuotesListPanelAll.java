package com.pengurus.crm.client.panels.center.quote;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotesListPanelAll extends QuotesListPanel {

	public QuotesListPanelAll() {
		modelList = new ModelList();
		add(modelList);
		setStyleName("background-panel");
	}
	
	@Override
	protected ListStore<QuoteModel> getList() {
		final ListStore<QuoteModel> list = new ListStore<QuoteModel>();
		AsyncCallback<Set<QuoteDTO> > callback = new AsyncCallback<Set<QuoteDTO> >() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error");
				mb.show();
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
