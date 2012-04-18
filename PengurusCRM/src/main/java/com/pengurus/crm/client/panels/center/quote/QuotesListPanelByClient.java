package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.PaginationRpcProxy;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.pagination.PagingCallbackWrapper;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public class QuotesListPanelByClient extends QuotesListPanelByUser {

	public QuotesListPanelByClient(UserDTO userDTO) {
		super(userDTO);
	}

	public QuotesListPanelByClient(UserDTO userDTO, int height, int width) {
		super(userDTO, height, width);
	}

	@Override
	protected void initPagination() {
		listPagination = new ListPagination<QuoteModel>(new PaginationRpcProxy<QuoteModel>() {

			@Override
			protected void load(PagingLoadConfigHelper loadConfig,
					AsyncCallback<PagingLoadResult<QuoteModel>> callback) {
				QuoteServiceAsync service = (QuoteServiceAsync) GWT
						.create(QuoteService.class);
				service.getPaginatedQuotesByClientId(loadConfig, userDTO.getId(), new PagingCallbackWrapper<QuoteModel>(callback));
			}
		});
		
	}

}
