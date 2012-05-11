package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.PaginationService;
import com.pengurus.crm.client.service.PaginationServiceAsync;
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
				PaginationServiceAsync service = (PaginationServiceAsync) GWT
						.create(PaginationService.class);
				service.getPaginatedAllQuotes(loadConfig, new PagingCallbackWrapper<QuoteModel>(callback));
			}
		});
	}
	
	@Override
	protected String getName() {
		return myConstants.QuotesList();
	}
}
