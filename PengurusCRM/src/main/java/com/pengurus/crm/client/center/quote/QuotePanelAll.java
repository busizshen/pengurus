package com.pengurus.crm.client.center.quote;

import com.extjs.gxt.ui.client.store.ListStore;
import com.pengurus.crm.client.models.QuoteModel;

public class QuotePanelAll extends QuotePanel {

	public QuotePanelAll(){
		
	}

	@Override
	protected ListStore<QuoteModel> getList() {
		return new ListStore<QuoteModel>();
	/*	final ExampleServiceAsync service = (ExampleServiceAsync) Registry.get(Examples.SERVICE);  
		  
	    RpcProxy<PagingLoadResult<Stock>> proxy = new RpcProxy<PagingLoadResult<Stock>>() {  
	      @Override  
	      public void load(Object loadConfig, AsyncCallback<PagingLoadResult<Stock>> callback) {  
	        service.getStocks((FilterPagingLoadConfig) loadConfig, callback);  
	      }  
	    };
	  
	    // loader  
	    final PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy) {  
	      @Override  
	      protected Object newLoadConfig() {  
	        BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();  
	        return config;  
	      }  

	    };  
	    loader.setRemoteSort(true);  
	  
	    return new ListStore<Stock>(loader); 
  */
		}
}
