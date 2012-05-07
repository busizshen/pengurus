package com.pengurus.crm.client.panels.center.quote;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.DateFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class QuotesListPanel extends ListPanel<QuoteModel> {

	ListPagination<QuoteModel> listPagination;

	protected abstract void initPagination();

	public QuotesListPanel(){
		super(360);
		setHeading("Quotes");
	}
	
	@Override
	protected ListStore<QuoteModel> getList() {
		return listPagination.getStore();
	}

	@Override
	protected void addGridPaging(ContentPanel cp, EditorGrid<QuoteModel> grid) {
		listPagination.addToGrid(cp, grid);
	}

	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfigMy();
		column.setId("id");
		column.setHeader("Id");
		column.setWidth(10);
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("status");
		column.setHeader("Status");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("client");
		column.setHeader("Client");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("supervisor");
		column.setHeader("Supervisor");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("description");
		column.setHeader("Description");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("preview");
		column.setHeader("Preview");
		column.setRenderer(getButtonRenderer());
		configs.add(column);

		/*
		 * column = new ColumnConfig(); column.setId("dateFrom");
		 * column.setHeader("Date From");
		 * column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
		 * configs.add(column);
		 * 
		 * column = new ColumnConfig(); column.setId("dateTo");
		 * column.setHeader("Date To");
		 * column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
		 * configs.add(column);
		 */
		return configs;
	}

	protected GridFilters getFilters() {
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
		statusStore.add(status("in_progress"));
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

	private GridCellRenderer<QuoteModel> getButtonRenderer() {

		GridCellRenderer<QuoteModel> buttonRenderer = new GridCellRenderer<QuoteModel>() {

			private boolean init;

			public Object render(final QuoteModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<QuoteModel> store, Grid<QuoteModel> grid) {
				if (!init) {
					init = true;
				}
				ButtonBar buttonBar = new ButtonBar();
				Button previewButton = new Button("Preview",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

									public void onFailure(Throwable t) {
										Window.Location
												.assign("/spring_security_login");
									}

									public void onSuccess(QuoteDTO result) {
										QuotePanelView qp = new QuotePanelView(
												result);
										qp.setAsMain();
									}
								};
								QuoteServiceAsync service = (QuoteServiceAsync) GWT
										.create(QuoteService.class);
								service.getQuote(model.getQuoteDTO().getId(),
										callback);

							}
						});
				previewButton.setToolTip("Click to see");
				previewButton.setWidth((grid.getColumnModel().getColumnWidth(
						colIndex) - 22) / 2);
				buttonBar.add(previewButton);
				
				if (AuthorizationManager.hasExecutiveAccess()) {
					Button editButton = new Button("Edit",
							new SelectionListener<ButtonEvent>() {
								@Override
								public void componentSelected(ButtonEvent ce) {
									AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

										public void onFailure(Throwable t) {
											Window.Location
													.assign("/spring_security_login");
										}

										public void onSuccess(QuoteDTO result) {
											QuotePanelEdit qp = new QuotePanelEdit(
													result);
											qp.setAsMain();
										}
									};
									QuoteServiceAsync service = (QuoteServiceAsync) GWT
											.create(QuoteService.class);
									service.getQuote(model.getQuoteDTO()
											.getId(), callback);

								}
							});
					editButton.setToolTip("Click to see");
					editButton.setWidth((grid.getColumnModel().getColumnWidth(
							colIndex) - 22) / 2);
					buttonBar.add(editButton);
				}
				return buttonBar;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected void setStyle(ContentPanel cp) {

	}

}
