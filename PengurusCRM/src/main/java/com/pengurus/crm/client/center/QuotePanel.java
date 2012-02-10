package com.pengurus.crm.client.center;


import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;  
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;  
import com.extjs.gxt.ui.client.data.BaseModelData;  
import com.extjs.gxt.ui.client.data.ModelData;  
import com.extjs.gxt.ui.client.store.ListStore;  
import com.extjs.gxt.ui.client.widget.ContentPanel;  
import com.extjs.gxt.ui.client.widget.LayoutContainer;  
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;  
import com.extjs.gxt.ui.client.widget.grid.ColumnData;  
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;  
import com.extjs.gxt.ui.client.widget.grid.Grid;  
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;  
import com.extjs.gxt.ui.client.widget.grid.filters.BooleanFilter;  
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;  
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;  
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;  
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;  
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;  
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;  
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;  
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;  
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;  
import com.google.gwt.i18n.client.DateTimeFormat;  
import com.google.gwt.i18n.client.NumberFormat;  
import com.google.gwt.user.client.Element;  
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.entities.Quote;

public class QuotePanel implements CenterPanel  {
  
	public void LastEdited(){
		QuoteList ql = new QuoteList();
		MainWindow.addWidgetCenterPanel(ql);
        MessageBox.info("Message", "Krzysiek liże jaja i chuja i murzyńskie chuje!!", null);
	}

	@Override
	public void All() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Mine() {
		// TODO Auto-generated method stub
	}

	@Override
	public void TemplateList() {
		// TODO Auto-generated method stub
		
	}
	
	class QuoteList extends LayoutContainer{
		@Override  
		  protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index);  
		    setLayout(new FlowLayout(10));  
		    getAriaSupport().setPresentation(true);  
		  
		    ListStore<Quote> store = getList();//pobieramy liste quotes 
		  
		    /*final NumberFormat currency = NumberFormat.getCurrencyFormat();  
		    final NumberFormat number = NumberFormat.getFormat("0.00");  
		    final NumberCellRenderer<Grid<Stock>> numberRenderer = new NumberCellRenderer<Grid<Stock>>(currency);  
		  *//*
		    GridCellRenderer<Stock> change = new GridCellRenderer<Stock>() {  
		      public String render(Stock model, String property, ColumnData config, int rowIndex, int colIndex,  
		          ListStore<Stock> store, Grid<Stock> grid) {  
		        double val = (Double) model.get(property);  
		        String style = val < 0 ? "red" : GXT.isHighContrastMode ? "#00ff5a" : "green";  
		        return "<span style='font-weight: bold;color:" + style + "'>" + number.format(val) + "</span>";  
		      }  
		    };  
		  
		    GridCellRenderer<Stock> gridNumber = new GridCellRenderer<Stock>() {  
		      public String render(Stock model, String property, ColumnData config, int rowIndex, int colIndex,  
		          ListStore<Stock> store, Grid<Stock> grid) {  
		        return numberRenderer.render(null, property, model.get(property));  
		      }  
		    };  */
		  
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("name");  
		    column.setHeader("Company");  
		    column.setWidth(200);  
		    configs.add(column);  
		  
		    column = new ColumnConfig();  
		    column.setId("symbol");  
		    column.setHeader("Symbol");  
		    column.setWidth(100);  
		    configs.add(column);  
		  
		    column = new ColumnConfig();  
		    column.setId("last");  
		    column.setHeader("Last");  
		    column.setAlignment(HorizontalAlignment.RIGHT);  
		    column.setWidth(100);  
		    //column.setRenderer(gridNumber);  
		    configs.add(column);  
		  
		    column = new ColumnConfig("change", "Change", 100);  
		    column.setAlignment(HorizontalAlignment.RIGHT);  
		   // column.setRenderer(change);  
		    configs.add(column);  
		  
		    column = new ColumnConfig("date", "Last Updated", 120);  
		    column.setAlignment(HorizontalAlignment.RIGHT);  
		    column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));  
		    configs.add(column);  
		  
		    column = new ColumnConfig("split", "Split", 75);  
		   /* column.setRenderer(new GridCellRenderer<Stock>() {  
		      public Object render(Stock model, String property, ColumnData config, int rowIndex, int colIndex,  
		          ListStore<Stock> store, Grid<Stock> grid) {  
		        Boolean b = model.get(property);  
		        return b != null && b.booleanValue() ? "Yes" : "No";  
		      }  
		    });  */
		    configs.add(column);  
		  
		    column = new ColumnConfig("type", "Type", 75);  
		    configs.add(column);  
		  
		    ColumnModel cm = new ColumnModel(configs);  
		  
		    ContentPanel cp = new ContentPanel();  
		    cp.setBodyBorder(true);  
		   // cp.setIcon(Resources.ICONS.table());  
		    cp.setHeading("Remote Filter Grid");  
		    cp.setButtonAlign(HorizontalAlignment.CENTER);  
		    cp.setLayout(new FitLayout());  
		    cp.setSize(660, 300);  
		  
		    GridFilters filters = new GridFilters();  
		    NumericFilter last = new NumericFilter("last");  
		    NumericFilter filter = new NumericFilter("change");  
		    StringFilter nameFilter = new StringFilter("name");  
		    DateFilter dateFilter = new DateFilter("date");  
		    BooleanFilter booleanFilter = new BooleanFilter("split");  
		  
		    ListStore<ModelData> typeStore = new ListStore<ModelData>();  
		    typeStore.add(type("Auto"));  
		    typeStore.add(type("Media"));  
		    typeStore.add(type("Medical"));  
		    typeStore.add(type("Tech"));  
		    ListFilter listFilter = new ListFilter("type", typeStore);  
		    listFilter.setDisplayProperty("type");  
		  
		    filters.addFilter(last);  
		    filters.addFilter(nameFilter);  
		    filters.addFilter(filter);  
		    filters.addFilter(dateFilter);  
		    filters.addFilter(booleanFilter);  
		    filters.addFilter(listFilter);  
		  
		    final Grid<Quote> grid = new Grid<Quote>(store, cm);  
		  /*  grid.addListener(Events.Attach, new Listener<BaseEvent>() {  
		      public void handleEvent(BaseEvent be) {  
		        loader.load(0, 25);  
		      }  
		    });*/  
		    grid.getView().setForceFit(true);  
		    grid.setStyleAttribute("borderTop", "none");  
		    grid.setAutoExpandColumn("name");  
		    grid.setBorders(false);  
		    grid.setStripeRows(true);  
		    grid.setColumnLines(true);  
		    grid.addPlugin(filters);  
		    cp.add(grid);  
	  
		 /*   final PagingToolBar toolBar = new PagingToolBar(25);  
		    toolBar.bind(loader);   ??
		  
		    cp.setBottomComponent(toolBar);  
*/		  
		    cp.add(new Button("kurwa"));
		    add(cp);  
		  }  
		  
		  private ModelData type(String type) {  
		    ModelData model = new BaseModelData();  
		    model.set("type", type);  
		    return model;  
		  }  
          
		  private ListStore<Quote> getList(){
/*			    final ExampleServiceAsync service = (ExampleServiceAsync) Registry.get(Examples.SERVICE);  
				  
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
			  return new ListStore<Quote>();
		  }
		
	}
}


