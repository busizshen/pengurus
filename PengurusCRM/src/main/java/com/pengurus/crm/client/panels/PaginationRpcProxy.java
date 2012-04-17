package com.pengurus.crm.client.panels;

import com.extjs.gxt.ui.client.data.Model;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.shared.pagination.PagingLoadConfigHelper;

public abstract class PaginationRpcProxy<T extends Model> extends RpcProxy<PagingLoadResult<T>>{

	@Override
	protected void load(Object loadConfig,
			AsyncCallback<PagingLoadResult<T>> callback) {
		PagingLoadConfig config = (PagingLoadConfig) loadConfig;
		PagingLoadConfigHelper configHelper = new PagingLoadConfigHelper(config.getSortField(), config.getSortDir().toString(), config.getOffset(), config.getLimit());
		load(configHelper, callback);
	}
	
	abstract protected void load(PagingLoadConfigHelper loadConfig, AsyncCallback<PagingLoadResult<T>> callback);

}
