package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
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
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public class TranslationsListPanel extends ListPanel<TranslationModel> {

	ListStore<TranslationModel> list;
	ModelList ml;
	TranslationModel translation;
	private Listener<DomEvent> listenerChosen;

	public TranslationsListPanel(
			ListStore<TranslationModel> listTranslationModel, Listener<DomEvent> listener) {
		list = listTranslationModel;
		listenerChosen = listener;
		setHeaderVisible(false);
		ml = new ModelList();
		add(ml);
		expand();
	}

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfigMy();
		column = new ColumnConfigMy();
		column.setId("type");
		column.setHeader("Type");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("from");
		column.setHeader("From");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("to");
		column.setHeader("To");
		configs.add(column);
		
		column = new ColumnConfigMy();
		column.setId("choose");
		column.setHeader("Choose");
		column.setRenderer(getButtonRenderer());
		configs.add(column);

		return configs;
	}

	@Override
	protected String getName() {
		return "Choose Translation";
	}

	@Override
	protected ListStore<TranslationModel> getList() {
		return list;
	}

	@Override
	protected GridFilters getFilters() {
		return new GridFilters();
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		cp.setCollapsible(true);
		cp.setAnimCollapse(true);
		cp.collapse();

	}
	private GridCellRenderer<TranslationModel> getButtonRenderer() {

		GridCellRenderer<TranslationModel> buttonRenderer = new GridCellRenderer<TranslationModel>() {

			private boolean init;

			public Object render(final TranslationModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<TranslationModel> store, Grid<TranslationModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<TranslationModel>>() {

								public void handleEvent(GridEvent<TranslationModel> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}
				Button b = new Button("CHOOSE",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								translation = model;
							}
						});
				b.setToolTip("Click to see");
				b.addListener(Events.OnClick, listenerChosen);
				return b;
			}
		};
		return buttonRenderer;
	}

	public TranslationModel getTranslation() {
		return translation;
	}

}
