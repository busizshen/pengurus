package com.pengurus.crm.client.panels.center.user.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.ClientModel;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.client.service.ClientService;
import com.pengurus.crm.client.service.ClientServiceAsync;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientsListPanel extends ListPanel<ClientModel> {

	ListStore<ClientModel> clients = new ListStore<ClientModel>();
	public ClientsListPanel(){
		
	}
	
	public ModelList getPanel(){
		ModelList ql = new ModelList();
		return ql;
	}
	
	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs   = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId("id");
	    column.setHeader("Id");
	    configs.add(column);  
	  
/*	    column = new ColumnConfig();  
	    column.setId("type");  
	    column.setHeader("Type");  
	    configs.add(column);  */
	    
	    column = new ColumnConfig();  
	    column.setId("username");  
	    column.setHeader("Username");
	    configs.add(column);
	      

	    column = new ColumnConfig();  
	    column.setId("description");  
	    column.setHeader("Description");  
	    configs.add(column);  

	    column = new ColumnConfig(); 
	    column.setId("choose");  
	    column.setHeader("Choose");  
	    column.setRenderer(getButtonRenderer()); 
	    configs.add(column);  
	    return configs;
	}


	private GridCellRenderer getButtonRenderer() { 
		GridCellRenderer<ClientModel> buttonRenderer = new GridCellRenderer<ClientModel>() {  
			  
		      private boolean init;  
		  
		      public Object render(final ClientModel model, String property, ColumnData config, final int rowIndex,  
		          final int colIndex, ListStore<ClientModel> store, Grid<ClientModel> grid) {  
		        if (!init) {  
		          init = true;  
		          grid.addListener(Events.OnClick, new Listener<GridEvent<ClientModel>>() {  
		  
		            public void handleEvent(GridEvent<ClientModel> be) {  
		              for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {  
		                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null  
		                    && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {  
		                  ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);  
		                }  
		              }  
		            }  
		          });  
		        }  
		   
		        Button b = new Button("Choose", new SelectionListener<ButtonEvent>() {  
		          @Override  
		          public void componentSelected(ButtonEvent ce) {
		              //heheh jak to zrobić 
		        	  //może przesyłać jakiegoś listenera który będzie tu coś robił
		        	  //nieźle zapierdolę się z tym listnenrami - czuje się jak w pracy masakra 
		          }  
		        });  
		        b.setToolTip("Click to see");  
		  
		        return b;  
		      }  
		    };
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		return "Clients list";
	}

	@Override
	protected ListStore<ClientModel> getList() {
		final ListStore<ClientModel> list = new ListStore<ClientModel>();
		AsyncCallback<Set<ClientDTO> > callback = new AsyncCallback<Set<ClientDTO> >() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<ClientDTO> result) {
				for(ClientDTO q: result){
					list.add(new ClientModel(q));
				}
			}
		};
		ClientServiceAsync service = (ClientServiceAsync) GWT
				.create(ClientService.class);
		service.getClients(callback);

		return list;
	}

	@Override
	protected GridFilters getFilters() {
		 GridFilters filters = new GridFilters();  
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		cp.setCollapsible(true);
		cp.setAnimCollapse(true);
	}

}
