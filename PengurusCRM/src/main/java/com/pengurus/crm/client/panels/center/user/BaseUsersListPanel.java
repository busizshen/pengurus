package com.pengurus.crm.client.panels.center.user;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public abstract class BaseUsersListPanel<T extends UserModel> extends ListPanel<T> {

	ListStore<T> clients = new ListStore<T>();
	
	public BaseUsersListPanel(int height){
		super(height);
	}
	
	public BaseUsersListPanel(){
		super(400);
	}
	
	@Override
	protected void setStyle(ContentPanel cp) {
	}
	
	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();  
		return filters;
	}
	
	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs   = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfigMy();  
	    column.setId("id");
	    column.setHeader(myConstants.Id());
	    configs.add(column);  
	  
/*	    column = new ColumnConfig();  
	    column.setId("type");  
	    column.setHeader("Type");  
	    configs.add(column);  */
	    
	    column = new ColumnConfigMy();  
	    column.setId("username");  
	    column.setHeader(myConstants.Username());
	    configs.add(column);
	      

	    column = new ColumnConfigMy();  
	    column.setId("description");  
	    column.setHeader(myConstants.Description());  
	    configs.add(column); 
	    
	    column = new ColumnConfigMy();
	    column.setId("fullName");
	    column.setHeader(myConstants.FullName());
	    configs.add(column);

	    column = new ColumnConfigMy(); 
	    column.setId("choose");  
	    column.setHeader(myConstants.Choose());  
	    column.setRenderer(getButtonRenderer()); 
	    configs.add(column);
	   
	    return configs;
	}

	protected abstract GridCellRenderer<T> getButtonRenderer();

}
