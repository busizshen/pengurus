package com.pengurus.crm.client.panels.center.user.create;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ColumnModelEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.pengurus.crm.client.models.StringModel;

public class StringSetPanel extends ContentPanel {

	private EditorGrid<StringModel> grid;
	final ListStore<StringModel> store;

	protected void doAutoHeight() {
		if (grid.isViewReady()) {
			grid.getView().getScroller()
					.setStyleAttribute("overflowY", "hidden");
			setHeight((grid.getView().getBody().isScrollableX() ? 19 : 0)
					+ grid.el().getFrameWidth("tb")
					+ grid.getView().getHeader().getHeight() + getFrameHeight()
					+ grid.getView().getBody().firstChild().getHeight());
		}
	}

	public StringSetPanel(final String header, final String label) {
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		final CheckBoxSelectionModel<StringModel> checkBoxSelection = new CheckBoxSelectionModel<StringModel>();
		configs.add(checkBoxSelection.getColumn());

		ColumnConfig column = new ColumnConfig();
		column.setId("value");
		column.setHeader(header);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		store = new ListStore<StringModel>();

		ColumnModel cm = new ColumnModel(configs);

		setFrame(false);
		setLayout(new FitLayout());

		grid = new EditorGrid<StringModel>(store, cm);
		grid.setSelectionModel(checkBoxSelection);
		grid.setAutoExpandColumn("value");
		setHeaderVisible(false);
		grid.setBorders(false);
		grid.addPlugin(checkBoxSelection);
		add(grid);

		ToolBar toolBar = new ToolBar();
		Button add = new Button("Add");
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				grid.stopEditing();
				store.insert(new StringModel("new " + label), store.getCount());
				doAutoHeight();
				grid.startEditing(0, 0);

			}

		});
		toolBar.add(add);
		Button remove = new Button("Remove");
		remove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				grid.stopEditing();
				for (StringModel stringModel : checkBoxSelection
						.getSelectedItems()) {
					store.remove(stringModel);
				}
				doAutoHeight();
				grid.startEditing(0, 0);
			}

		});
		toolBar.add(remove);
		setBottomComponent(toolBar);
		setButtonAlign(HorizontalAlignment.CENTER);

		grid.getStore().addStoreListener(new StoreListener<StringModel>() {
			@Override
			public void storeUpdate(StoreEvent<StringModel> se) {
				super.storeUpdate(se);
				store.commitChanges();
			}
		});

		grid.addListener(Events.ViewReady, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				grid.getStore().addListener(Store.Add,
						new Listener<StoreEvent<StringModel>>() {
							public void handleEvent(StoreEvent<StringModel> be) {
								doAutoHeight();
							}
						});
				doAutoHeight();
			}
		});
		grid.addListener(Events.ColumnResize, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				doAutoHeight();
			}
		});
		grid.getColumnModel().addListener(Events.HiddenChange,
				new Listener<ColumnModelEvent>() {
					public void handleEvent(ColumnModelEvent be) {
						doAutoHeight();
					}
				});
		
	}
	
	
	public void setData(List<String> dataList) {
		if (dataList == null) return;
		for (String data: dataList) {
			store.add(new StringModel(data));
		}
	}
	
	public List<String> getData() {
		List<String> data = new ArrayList<String>();
		for (StringModel model: store.getModels()) {
			data.add(model.getValue());
		}
		return data;
	}
}
