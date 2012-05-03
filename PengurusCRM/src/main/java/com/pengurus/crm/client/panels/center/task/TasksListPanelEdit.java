package com.pengurus.crm.client.panels.center.task;

import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TasksListPanelEdit extends TasksListPanel {

	JobDTO jobDTO;
	ProjectDTO projectDTO;

	public TasksListPanelEdit(Set<TaskDTO> set, JobDTO jobDTO,
			ProjectDTO projectDTO) {
		super();
		this.jobDTO = jobDTO;
		this.projectDTO = projectDTO;
		for (TaskDTO task : set)
			tasks.add(new TaskModel(task));
		initPanel();
	}

	protected void initPanel() {
		modelList = new ModelList();
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);

		hp.add(addButtonPanel());
		hp.add(modelList);
		add(hp);
	}

	private FormPanel addButtonPanel() {
		FormPanel formPanel = new FormPanel();
		final TasksListPanelEdit taskListPanel = this;
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
								jobDTO, projectDTO, taskListPanel);
						Listener<DomEvent> listenerCreateJob = new Listener<DomEvent>() {
							@Override
							public void handleEvent(DomEvent be) {
								if (taskPanel.getTaskDTO() != null) {
									/*
									 * ml.getGrid().stopEditing();
									 * ml.getStore().add( new
									 * TaskModel(taskPanel .getTaskDTO()));
									 * ml.getGrid().startEditing(0, 0);
									 */
									/*
									 * jobDTO.getTask()
									 * .add(taskPanel.getTaskDTO());
									 */
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

	protected GridCellRenderer<TaskModel> getButtonRenderer() {
		GridCellRenderer<TaskModel> buttonRenderer = new GridCellRenderer<TaskModel>() {

			private boolean init;

			public Object render(final TaskModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<TaskModel> store, Grid<TaskModel> grid) {
				if (!init) {
					init = true;
				}
				ButtonBar buttonBar = new ButtonBar();
				Button b = new Button("PREVIEW",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								TaskPanelView taskPanel = new TaskPanelView(
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
	protected ListStore<TaskModel> getList() {
		return tasks;
	}

}
