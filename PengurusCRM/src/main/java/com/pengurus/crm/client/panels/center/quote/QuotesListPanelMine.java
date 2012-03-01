package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.models.QuoteModel;

public class QuotesListPanelMine extends QuotesListPanel {

	public QuotesListPanelMine(){
		ModelList ql = new ModelList();
		MainWindow.addCenterPanel(ql);
	}
	
	@Override
	protected ListStore<QuoteModel> getList() {
		return new ListStore<QuoteModel>();
	}

	@Override
	protected String getName() {
		return "Quote List - Mine";
	}

}
