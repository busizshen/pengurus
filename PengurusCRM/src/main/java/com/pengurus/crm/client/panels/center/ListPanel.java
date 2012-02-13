package com.pengurus.crm.client.panels.center;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;


public abstract class ListPanel<T extends ModelData>  {

	public class ModelList extends LayoutContainer{

		@Override  
		protected void onRender(Element parent, int index) { 
			super.onRender(parent, index);  
		    setLayout(new FlowLayout(10));  
		    getAriaSupport().setPresentation(true);  
		  
		    ListStore<T> store = getList();
		    
		    
		    List<ColumnConfig> configs = getColumns();		  
		    ColumnModel cm = new ColumnModel(configs);  
		  
		    ContentPanel cp = new ContentPanel();  
		    cp.setBodyBorder(true);  
		   // cp.setIcon(Resources.ICONS.table());  
		    cp.setHeading(getName());  
		    cp.setButtonAlign(HorizontalAlignment.CENTER);  
		    cp.setLayout(new FitLayout());  
		    cp.setSize(660, 300);  
		  
		    GridFilters filters = getFilters();
		  
		    final Grid<T> grid = new Grid<T>(store, cm);  
		 /*   grid.addListener(Events.Attach, new Listener<BaseEvent>() {  
		      public void handleEvent(BaseEvent be) {  
		        loader.load(0, 25);  
		      }  
		    });*/  
		    grid.getView().setForceFit(true);  
		    grid.setStyleAttribute("borderTop", "none"); 
		    grid.setAutoExpandColumn("id"); 
		    grid.setBorders(false);  
		    grid.setStripeRows(true);  
		    grid.setColumnLines(true);  
		    grid.addPlugin(filters);  
		    cp.add(grid);  
	  
		    add(cp);  
		}
		
	}
	
	protected abstract List<ColumnConfig> getColumns();

	protected abstract String getName();

	protected abstract ListStore<T> getList();
	
	protected abstract GridFilters getFilters();
	
	
}

