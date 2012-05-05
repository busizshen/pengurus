package com.pengurus.crm.client.panels.center;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

public abstract class ListPanel<T extends ModelData> extends MainPanel {
	private EditorGrid<T> grid;
	private ListStore<T> store;
	protected ModelList modelList;

	public class ModelList extends LayoutContainer {

		protected ContentPanel cp = new ContentPanel();
		private int widthVal;
		private int heightVal;
		
		public ModelList() {
			this.widthVal = 800;
			this.heightVal = 300;
			initPanel();
		}
		public ModelList(int height, int width) {
			this.widthVal = width;
			this.heightVal = height;
			initPanel();
		}

		private void initPanel() {
			setStyle(cp);
			setLayout(new FlowLayout(10));
			getAriaSupport().setPresentation(true);

			store = getList();

			List<ColumnConfig> configs = getColumns();
			ColumnModel cm = new ColumnModel(configs);
			setStyle(cp);
			cp.setHeading(getName());
			cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FillLayout());
			cp.setWidth(widthVal);
			cp.setHeight(heightVal);

			GridFilters filters = getFilters();

			grid = new EditorGrid<T>(store, cm);
			grid.getView().setForceFit(true);
			grid.setAutoExpandColumn("id");
			grid.setStripeRows(true);
			grid.setColumnLines(true);
			grid.addPlugin(filters);
			addGridPaging(cp, grid);
			cp.add(grid);

			add(cp);
		}
		public EditorGrid<T> getGrid() {
			return grid;
		}

		public ListStore<T>  getStore() {
			return store;
		}
	}
	
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
	}
	
	public class ColumnConfigMy extends ColumnConfig {
		
		public ColumnConfigMy(){
			super();
			this.setWidth(100);
		}
	}

	public ListStore<T> getStore() {
		return store;
	}

	public void setStore(ListStore<T> store) {
		this.store = store;
	}

	public EditorGrid<T> getGrid() {
		return grid;
	}

	public void setGrid(EditorGrid<T> grid) {
		this.grid = grid;
	}

	protected abstract List<ColumnConfig> getColumns();

	protected abstract String getName();

	protected abstract ListStore<T> getList();

	protected abstract GridFilters getFilters();

	protected abstract void setStyle(ContentPanel cp);
	
	
	protected void addGridPaging(ContentPanel cp, final EditorGrid<T> grid) {
		return;
	}

	public void setList(List<T> list) {
	//	setHeaderVisible(false);
		grid.stopEditing();
		store.removeAll();
		store.add(list);
		grid.startEditing(0, 0);
	}
	
	public ModelList getModelList(){
		return modelList;
	}

}
