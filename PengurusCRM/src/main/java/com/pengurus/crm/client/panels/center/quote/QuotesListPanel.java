package com.pengurus.crm.client.panels.center.quote;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
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
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class QuotesListPanel extends ListPanel<QuoteModel> {

	ModelList ql;

	public QuotesListPanel() {
		add(new ModelList());
	}

	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

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
		configs.add(column);

		column = new ColumnConfig();
		column.setId("supervisor");
		column.setHeader("Supervisor");
		configs.add(column);

		column = new ColumnConfig();
		column.setId("description");
		column.setHeader("Description");
		configs.add(column);

		column = new ColumnConfig();
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

	private GridCellRenderer<QuoteModel> getButtonRenderer() {

		GridCellRenderer<QuoteModel> buttonRenderer = new GridCellRenderer<QuoteModel>() {

			private boolean init;

			public Object render(final QuoteModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<QuoteModel> store, Grid<QuoteModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<QuoteModel>>() {

								public void handleEvent(GridEvent<QuoteModel> be) {
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
				ButtonBar buttonBar = new ButtonBar();
				Button b = new Button("PREVIEW",
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
										qp.getPanel();
									}
								};
								QuoteServiceAsync service = (QuoteServiceAsync) GWT
										.create(QuoteService.class);
								service.getQuote(model.getQuoteDTO().getId(),
										callback);

							}
						});
				b.setToolTip("Click to see");
				buttonBar.add(b);
				if (AuthorizationManager.hasExecutiveAccess()) {
					b = new Button("EDIT",
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
											qp.getPanel();
										}
									};
									QuoteServiceAsync service = (QuoteServiceAsync) GWT
											.create(QuoteService.class);
									service.getQuote(model.getQuoteDTO()
											.getId(), callback);

								}
							});
					b.setToolTip("Click to see");
					buttonBar.add(b);
				}
				return buttonBar;
			}
		};
		return buttonRenderer;
	}

	protected abstract ListStore<QuoteModel> getList();

	@Override
	protected void setStyle(ContentPanel cp) {

	}

}
