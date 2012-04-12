package com.pengurus.crm.client.panels.center.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.panels.center.ListPanel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TasksListPanel extends ListPanel<TaskModel> {
	
	ModelList ml;
	JobDTO jobDTO;
	ProjectDTO projectDTO;
	private ListStore<TaskModel> tasks;

	public TasksListPanel(Set<TaskDTO> set, JobDTO jobDTO, ProjectDTO projectDTO) {
		super();
		this.jobDTO = jobDTO;
		this.projectDTO = projectDTO;
		tasks = new ListStore<TaskModel>();
		for (TaskDTO task : set)
			tasks.add(new TaskModel(task));
		ml = new ModelList();
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		
		hp.add(addButtonPanel());
		hp.add(ml);
		add(hp);
	}

	protected void refreshList(TaskDTO taskDTO){
		ml.getGrid().stopEditing();
		ml.getStore().add(
				new TaskModel(taskDTO));
		ml.getGrid().startEditing(0, 0);
	}
	private FormPanel addButtonPanel() {
		FormPanel formPanel = new FormPanel();
		final TasksListPanel taskListPanel = this;
		formPanel.setHeaderVisible(false);
		formPanel.setFrame(false);
		Button b = new Button("Create new",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						final Window window = new Window();
						window.setAutoHeight(true);
						window.setAutoWidth(true);
						window.setClosable(false);
						final TaskPanelCreate taskPanel = new TaskPanelCreate(
								jobDTO,projectDTO,taskListPanel);
						Listener<DomEvent> listenerCreateJob = new Listener<DomEvent>() {
							@Override
							public void handleEvent(DomEvent be) {
								if (taskPanel.getTaskDTO() != null) {
							/*		ml.getGrid().stopEditing();
									ml.getStore().add(
											new TaskModel(taskPanel
													.getTaskDTO()));
									ml.getGrid().startEditing(0, 0);*/
/*									jobDTO.getTask()
											.add(taskPanel.getTaskDTO());*/
									window.hide();
								}
							}
						};
						Listener<DomEvent> listenerClose = new Listener<DomEvent>() {
							@Override
							public void handleEvent(DomEvent be) {
								window.hide();
							}
						};
						taskPanel
								.setListeners(listenerClose, listenerCreateJob);
						window.add(taskPanel);
						window.show();
					}
				});
		formPanel.add(b);
		return formPanel;
	}

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfigMy column = new ColumnConfigMy();
		column.setId("id");
		column.setHeader("Id");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("status");
		column.setHeader("Status");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("expert");
		column.setHeader("Expert");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationFrom");
		column.setHeader("From");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("translationTo");
		column.setHeader("To");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("deadline");
		column.setHeader("Deadline");
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

	private GridCellRenderer<TaskModel> getButtonRenderer() {
		GridCellRenderer<TaskModel> buttonRenderer = new GridCellRenderer<TaskModel>() {

			private boolean init;

			public Object render(final TaskModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<TaskModel> store, Grid<TaskModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<TaskModel>>() {

								public void handleEvent(GridEvent<TaskModel> be) {
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
								TaskPanel taskPanel = new TaskPanel(
										model.getTaskDTO(), projectDTO);
								taskPanel.setAsMain();
							}
						});
				b.setToolTip("Click to see");
				buttonBar.add(b);
				if (AuthorizationManager.canChangeTask()) {
					b = new Button("DELETE",

					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {

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
		return "Tasks List Panel";
	}

	@Override
	protected ListStore<TaskModel> getList() {
		return tasks;
	}

	@Override
	protected GridFilters getFilters() {
		GridFilters filters = new GridFilters();
		return filters;
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		setHeading("Tasks");
		setAnimCollapse(true);
		setCollapsible(true);
	}

}
