package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.pengurus.crm.client.models.TranslationModel;

public class TranslationsListPanelChoose extends TranslationsListPanel {

	TranslationModel translation;
	private Listener<DomEvent> listenerChosen;

	public TranslationsListPanelChoose(
			ListStore<TranslationModel> listTranslationModel,
			Listener<DomEvent> listener) {
		super(400);
		list = listTranslationModel;
		listenerChosen = listener;
		modelList = new ModelList();
		add(modelList);
	}

	protected void addButton(List<ColumnConfig> configs) {
		ColumnConfig column = new ColumnConfigMy();
		column.setId("choose");
		column.setHeader("Choose");
		column.setRenderer(getButtonRenderer());
		configs.add(column);
	}

	@Override
	protected String getName() {
		return "Choose Translation";
	}

	private GridCellRenderer<TranslationModel> getButtonRenderer() {

		GridCellRenderer<TranslationModel> buttonRenderer = new GridCellRenderer<TranslationModel>() {

			private boolean init;

			public Object render(final TranslationModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<TranslationModel> store,
					Grid<TranslationModel> grid) {
				if (!init) {
					init = true;
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

	@Override
	protected void setStyle(ContentPanel cp) {
	//	setHeaderVisible(false);
		setAutoHeight(false);
		setAutoWidth(false);
		cp.setCollapsible(true);
		cp.setAnimCollapse(true);
		cp.collapse();
	}

}
