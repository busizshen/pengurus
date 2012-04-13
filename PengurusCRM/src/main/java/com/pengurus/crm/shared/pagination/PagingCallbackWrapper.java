package com.pengurus.crm.shared.pagination;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PagingCallbackWrapper<T> implements AsyncCallback<PagingLoadResultHelper<T>> {

	private AsyncCallback<PagingLoadResult<T>> callback;

	public PagingCallbackWrapper(AsyncCallback<PagingLoadResult<T>> callback) {
		super();
		this.callback = callback;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		callback.onFailure(caught);		
	}

	@Override
	public void onSuccess(PagingLoadResultHelper<T> result) {
		callback.onSuccess(new BasePagingLoadResult<T>(result.getData(), result.getOffset(), result.getTotalLength()));
	}
}