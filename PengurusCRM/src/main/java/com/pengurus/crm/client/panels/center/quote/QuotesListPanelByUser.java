package com.pengurus.crm.client.panels.center.quote;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public class QuotesListPanelByUser extends QuotesListPanel {

	UserDTO userDTO;

	public QuotesListPanelByUser(UserDTO userDTO) {
		this.userDTO = userDTO;
		quotesList = new ModelList();
		add(quotesList);
	}

	@Override
	protected ListStore<QuoteModel> getList() {
		final ListStore<QuoteModel> list = new ListStore<QuoteModel>();
		AsyncCallback<Set<QuoteDTO>> callback = new AsyncCallback<Set<QuoteDTO>>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error");
				mb.show();
			}

			public void onSuccess(Set<QuoteDTO> result) {

				for (QuoteDTO q : result) {
					list.add(new QuoteModel(q));
				}
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);

		if (AuthorizationManager.isClient()) {
			service.getQuoteByClientId(userDTO.getId(), callback);
		} else {
			service.getQuoteBySupervisorId(userDTO.getId(), callback);
		}
		return list;
	}

	@Override
	protected String getName() {
		return "Quote List - Mine";
	}

}
