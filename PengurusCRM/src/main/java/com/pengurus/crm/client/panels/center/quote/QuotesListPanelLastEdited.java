package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class QuotesListPanelLastEdited extends QuotesListPanel{

	public QuotesListPanelLastEdited(){
		initPagination();
		modelList = new ModelList();
		MainWindow.addCenterPanel(modelList);
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
	protected void initPagination() {
		listPagination = new ListPagination<QuoteModel>(new PaginationRpcProxy<QuoteModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<QuoteModel>> callback) {
				QuoteServiceAsync service = (QuoteServiceAsync) GWT
						.create(QuoteService.class);
				service.getPaginatedAllQuotes(loadConfig, new PagingCallbackWrapper<QuoteModel>(callback));
			}
		});
	}

	@Override
	protected String getName() {
		return "Quote List - Last Edited";
	}

}
