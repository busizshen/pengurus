package com.pengurus.crm.client.center.quote;


import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.pengurus.crm.client.models.QuoteModel;

public abstract class QuotePanel  {
  	
	class QuoteList extends LayoutContainer{
		@Override  
		  protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index);  
		    setLayout(new FlowLayout(10));  
		    getAriaSupport().setPresentation(true);  
		  
		    ListStore<QuoteModel> store = getList();//pobieramy liste quotes tu trzeba zrobić odpowiedniego callbacka
		  
		    
		  /*  final NumberFormat currency = NumberFormat.getCurrencyFormat();  
		    final NumberFormat number = NumberFormat.getFormat("0.00");  
		    final NumberCellRenderer<Grid<QuoteDTO>> numberRenderer = new NumberCellRenderer<Grid<QuoteDTO>>(currency);  
		  
		    GridCellRenderer<QuoteDTO> change = new GridCellRenderer<QuoteDTO>() {  
		      public String render(QuoteDTO model, String property, ColumnData config, int rowIndex, int colIndex,  
		          ListStore<QuoteDTO> store, Grid<QuoteDTO> grid) {  
		        double val = (Double) model.get(property);  
		        String style = val < 0 ? "red" : GXT.isHighContrastMode ? "#00ff5a" : "green";  
		        return "<span style='font-weight: bold;color:" + style + "'>" + number.format(val) + "</span>";  
		      }  
		    };  
		  
		    GridCellRenderer<QuoteDTO> gridNumber = new GridCellRenderer<QuoteDTO>() {  
		      public String render(QuoteDTO model, String property, ColumnData config, int rowIndex, int colIndex,  
		          ListStore<QuoteDTO> store, Grid<QuoteDTO> grid) {  
		        return numberRenderer.render(null, property, model.get(property));  
		      }  
		    };  
		  */
		    List<ColumnConfig> configs = getColumns();		  
		    ColumnModel cm = new ColumnModel(configs);  
		  
		    ContentPanel cp = new ContentPanel();  
		    cp.setBodyBorder(true);  
		   // cp.setIcon(Resources.ICONS.table());  
		    cp.setHeading("Quote List");  
		    cp.setButtonAlign(HorizontalAlignment.CENTER);  
		    cp.setLayout(new FitLayout());  
		    cp.setSize(660, 300);  
		  
		    GridFilters filters = getFilters();
		  
		    final Grid<QuoteModel> grid = new Grid<QuoteModel>(store, cm);  
		 /*   grid.addListener(Events.Attach, new Listener<BaseEvent>() {  
		      public void handleEvent(BaseEvent be) {  
		        loader.load(0, 25);  
		      }  
		    });*/  
		    grid.getView().setForceFit(true);  
		    grid.setStyleAttribute("borderTop", "none"); 
		    /* grid.setAutoExpandColumn("name"); */ 
		    grid.setBorders(false);  
		    grid.setStripeRows(true);  
		    grid.setColumnLines(true);  
		    grid.addPlugin(filters);  
		    cp.add(grid);  
	  
		 /*   final PagingToolBar toolBar = new PagingToolBar(25);  
		    toolBar.bind(loader);   
		  
		    cp.setBottomComponent(toolBar);  
*/		  
		    add(cp);  
		  }  
		  
		  private List<ColumnConfig> getColumns() {
			  	List<ColumnConfig> configs   = new ArrayList<ColumnConfig>();  
			  
			    ColumnConfig column = new ColumnConfig();  
			    column.setId("id");  
			    column.setHeader("Id");
			    configs.add(column);  
			  
			    column = new ColumnConfig();  
			    column.setId("status");  
			    column.setHeader("Status");
			    configs.add(column);
			  
			    column = new ColumnConfig();  
			    column.setId("client");  
			    column.setHeader("Client");
			    //column.setRenderer(gridNumber);  
			    configs.add(column);  
			  
			    column = new ColumnConfig();  
			    column.setId("supervisor");  
			    column.setHeader("Supervisor");
			    //column.setRenderer(gridNumber);  
			    configs.add(column);  
			      

			    column = new ColumnConfig();  
			    column.setId("description");  
			    column.setHeader("Description");
			    //column.setRenderer(gridNumber);  
			    configs.add(column);  
			    
/*			    column = new ColumnConfig();  
			    column.setId("dateFrom");  
			    column.setHeader("Date From");
			    column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));  
			    configs.add(column);  
			    
			    column = new ColumnConfig();  
			    column.setId("dateTo");  
			    column.setHeader("Date To");
			    column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));  
			    configs.add(column);  */
			    return configs;
		}

		private GridFilters getFilters() {
			    GridFilters filters = new GridFilters();  
			    NumericFilter idFilter = new NumericFilter("id");    
			    StringFilter clientFilter = new StringFilter("client");  
			    StringFilter supervisorFilter = new StringFilter("supervisor");  
			    StringFilter descriptionFilter = new StringFilter("description"); 
			    DateFilter dateFromFilter = new DateFilter("dateFrom");
			    DateFilter dateToFilter = new DateFilter("dateTo");
			  
			    ListStore<ModelData> statusStore = new ListStore<ModelData>();  
			    statusStore.add(status("open"));  
			    statusStore.add(status("canceled"));  
			    statusStore.add(status("inProgress"));  
			    statusStore.add(status("resolved"));  
			    statusStore.add(status("verificated"));
			    statusStore.add(status("accepted"));
			    statusStore.add(status("accounted"));
			    statusStore.add(status("closed"));
			    ListFilter statusFilter = new ListFilter("status", statusStore);  
			    statusFilter.setDisplayProperty("status");  
			  
			    filters.addFilter(idFilter);  
			    filters.addFilter(clientFilter);
			    filters.addFilter(statusFilter);
			    filters.addFilter(supervisorFilter);  
			    filters.addFilter(dateFromFilter);  
			    filters.addFilter(dateToFilter);
			    filters.addFilter(descriptionFilter);  
			    
			    return filters;
		}

		private ModelData status(String type) {  
		    ModelData model = new BaseModelData();  
		    model.set("status", type);  
		    return model;  
		  }  
          
		  
		
	}
	
	protected abstract ListStore<QuoteModel> getList();
	
}



