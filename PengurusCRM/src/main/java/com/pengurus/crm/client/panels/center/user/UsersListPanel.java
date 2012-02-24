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

public abstract class UsersListPanel<T extends UserModel> extends ListPanel<T> {

	ListStore<T> clients = new ListStore<T>();
	public ModelList getPanel(){
		ModelList ql = new ModelList();
		return ql;
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

	protected abstract GridCellRenderer getButtonRenderer();

}
