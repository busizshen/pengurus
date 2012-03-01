package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.StatusDTO;

public class QuotesListPanelLastEdited extends QuotesListPanel{

	public QuotesListPanelLastEdited(){
		ModelList ql = new ModelList();
		MainWindow.addCenterPanel(ql);
	}
	
	protected ListStore<QuoteModel> getList(){
		ListStore<QuoteModel> quoteList = new ListStore<QuoteModel>();
		QuoteDTO qd = new QuoteDTO();
		qd.setDescription("assssss");
		qd.setStatus(StatusDTO.accepted);
		quoteList.add(new QuoteModel(qd));	
		qd = new QuoteDTO();
		qd.setDescription("B");
		qd.setStatus(StatusDTO.open);
		quoteList.add(new QuoteModel(qd));
		return quoteList;
  }

	@Override
	protected String getName() {
		return "Quote List - Last Edited";
	}

}
