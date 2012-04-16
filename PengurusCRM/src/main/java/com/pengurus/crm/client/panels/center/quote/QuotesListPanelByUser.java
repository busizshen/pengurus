package com.pengurus.crm.client.panels.center.quote;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class QuotesListPanelByUser extends QuotesListPanel {

	protected UserDTO userDTO;

	public QuotesListPanelByUser(UserDTO userDTO) {
		this.userDTO = userDTO;
		quotesList = new ModelList();
		add(quotesList);
	}

	public QuotesListPanelByUser(UserDTO userDTO, int height, int width) {
		this.userDTO = userDTO;
		quotesList = new ModelList(height, width);
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
		
		changeService(callback);
			
		return list;
	}

	protected abstract void changeService(AsyncCallback<Set<QuoteDTO>> callback);

	@Override
	protected String getName() {
		return userDTO.getFullName() + " quotes list";
	}

}
