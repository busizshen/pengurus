package com.pengurus.crm.client.panels;

import java.util.Map;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.Model;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.pengurus.crm.client.models.UserModel;

public class ListPagination<T extends Model> {

	private PagingToolBar toolBar;
	private ListStore<UserModel> store;
	private PagingLoader<PagingLoadResult<T>> loader;
	private int pageSize;
	
	public ListPagination(RpcProxy<PagingLoadResult<T>> proxy, int pageSize) {
		this.pageSize = pageSize;
		
		loader = new BasePagingLoader<PagingLoadResult<T>>(proxy) {
			@Override
			protected Object newLoadConfig() {
				BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
				return config;
			}
		};
		loader.setRemoteSort(true);
		store = new ListStore<UserModel>(loader);  
		toolBar = new PagingToolBar(pageSize);
		toolBar.bind(loader);
	}
	
	public ListPagination(RpcProxy<PagingLoadResult<T>> proxy) {
		this(proxy, 20);
	}

	public Listener<GridEvent<T>> getAttachListener(final EditorGrid<T> grid) {
		return new Listener<GridEvent<T>>() {
			public void handleEvent(GridEvent<T> be) {
				PagingLoadConfig config = new BaseFilterPagingLoadConfig();
				config.setOffset(0);
				config.setLimit(pageSize);

				Map<String, Object> state = grid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					int limit = (Integer) state.get("limit");
					config.setOffset(offset);
					config.setLimit(limit);
				}
				if (state.containsKey("sortField")) {
					config.setSortField((String) state.get("sortField"));
					config.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
				}
				loader.load(config);
			}
		};
	}
	
	public ListStore<UserModel> getStore() {
		return store;
	}

	public PagingToolBar getToolBar() {
		return toolBar;
	}

}
