package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.panels.ListPagination;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class TasksListPanelView extends TasksListPanel {

	protected ListPagination<TaskModel> listPagination;

	protected void initPanel() {
		initPaging();
		modelList = new ModelList();
		add(modelList);
	}

	protected void initPanel(int height, int width) {
		initPaging();
		modelList = new ModelList(height, width);
		add(modelList);
	}

	protected abstract void initPaging();

	@Override
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
				Button previewButton = new Button("Preview",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>() {

									public void onFailure(Throwable t) {
										MessageBox mb = new MessageBox();
										mb.setMessage("Server Error");
										mb.show();
									}

									@Override
									public void onSuccess(ProjectDTO projectDTO) {
										TaskPanelView taskPanel = new TaskPanelView(
												model.getTaskDTO(), projectDTO);
										taskPanel.setAsMain();
									}
								};
								ProjectServiceAsync service = (ProjectServiceAsync) GWT
										.create(ProjectService.class);
								service.getProjectByTaskId(model.getTaskDTO()
										.getId(), callback);
							}
						});
				previewButton.setToolTip("Click to see");
				buttonBar.add(previewButton);
				
				return buttonBar;
			}
		};
		return buttonRenderer;
	}

	protected ListStore<TaskModel> getList() {
		return listPagination.getStore();
	}

	@Override
	protected void addGridPaging(ContentPanel cp, EditorGrid<TaskModel> grid) {
		listPagination.addToGrid(cp, grid);
	}
}
