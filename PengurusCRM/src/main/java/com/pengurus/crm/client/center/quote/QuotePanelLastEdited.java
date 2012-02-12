package com.pengurus.crm.client.center.quote;

import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.shared.StatusDTO;

public class QuotePanelLastEdited extends QuotePanel{

	public QuotePanelLastEdited(){
		QuoteList ql = new QuoteList();
		MainWindow.addWidgetCenterPanel(ql);
	}
	
	protected ListStore<QuoteModel> getList(){
		ListStore<QuoteModel> quoteList = new ListStore<QuoteModel>();
		QuoteDTO qd = new QuoteDTO();
		qd.setDescription("assssss");
		qd.setStatus(StatusDTO.acceppted);
		quoteList.add(new QuoteModel(qd));	
		return quoteList;
  }

}
