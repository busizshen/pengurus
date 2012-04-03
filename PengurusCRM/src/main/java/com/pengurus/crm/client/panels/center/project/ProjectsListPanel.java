package com.pengurus.crm.client.panels.center.project;

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
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.ProjectModel;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class ProjectsListPanel extends ListPanel<ProjectModel> {

	ModelList ql;

	public ProjectsListPanel() {
		add(new ModelList());
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
		column.setId("supervisor");
		column.setHeader("Supervisor");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("client");
		column.setHeader("Client");
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
		
		return configs;

	}

	private GridCellRenderer<ProjectModel> getButtonRenderer() {
		GridCellRenderer<ProjectModel> buttonRenderer = new GridCellRenderer<ProjectModel>() {

			private boolean init;

			public Object render(final ProjectModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<ProjectModel> store, Grid<ProjectModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<ProjectModel>>() {

								public void handleEvent(GridEvent<ProjectModel> be) {
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
								AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>() {

									public void onFailure(Throwable t) {
									}

									public void onSuccess(ProjectDTO result) {
										ProjectPanelView projectPanel = new ProjectPanelView(result);
										projectPanel.setAsMain();
									}
								};
								ProjectServiceAsync service = (ProjectServiceAsync) GWT
										.create(ProjectService.class);
								service.getProject(model.getProjectDTO().getId(),
										callback);
							}
						});
				b.setToolTip("Click to see");
				buttonBar.add(b);
				if (AuthorizationManager.canChangeProject()) {
					b = new Button("EDIT",

							new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>() {

									public void onFailure(Throwable t) {
									}

									public void onSuccess(ProjectDTO result) {
										ProjectPanelEdit projectPanel = new ProjectPanelEdit(result);
										projectPanel.setAsMain();
									}
								};
								ProjectServiceAsync service = (ProjectServiceAsync) GWT
										.create(ProjectService.class);
								service.getProject(model.getProjectDTO().getId(),
										callback);
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

	@Override
	protected String getName() {
		return "Projects List Panel";
	}

	@Override
	protected abstract ListStore<ProjectModel> getList();

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {

	}

}
