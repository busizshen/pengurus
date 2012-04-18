package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class QuotesListPanelAll extends QuotesListPanel {
	
	public QuotesListPanelAll() {
		initPagination();
		modelList = new ModelList();
		add(modelList);
		setStyleName("background-panel");
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
		return "Quote List - All";
	}
}
