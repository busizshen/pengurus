package com.pengurus.crm.client.panels.center.job;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
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
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.client.models.QuoteModel;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.client.panels.center.quote.QuotePanelView;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class JobsListPanel extends ListPanel<JobModel> {

	protected ListStore<JobModel> jobs = new ListStore<JobModel>();
	protected QuoteDTO quoteDTO;

	public JobsListPanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO; 
		for (JobDTO j : quoteDTO.getJobs()) {
			this.jobs.add(new JobModel(j));
		}
	}

	public JobsListPanel(ProjectDTO projectDTO) {
		for (JobDTO j : projectDTO.getJobs()) {
			this.jobs.add(new JobModel(j));
		}
	}

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfigMy();
		column.setId("id");
		column.setHeader("Id");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("status");
		column.setHeader("Status");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("deadline");
		column.setHeader("Deadline");
		column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationFrom");
		column.setHeader("Translation From");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationTo");
		column.setHeader("Translation To");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("priceNumber");
		column.setHeader("Price");
		configs.add(column);

		/*
		 * column = new ColumnConfig(); column.setId("preview");
		 * column.setHeader("Preview"); column.setRenderer(getButtonRenderer());
		 * configs.add(column);
		 */
		return configs;
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

				return b;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		return "Job List";
	}

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		cp.setCollapsible(true);
		cp.setAnimCollapse(true);
	}

	@Override
	protected ListStore<JobModel> getList() {
		return jobs;
	}

	public abstract Widget getPanel();

}