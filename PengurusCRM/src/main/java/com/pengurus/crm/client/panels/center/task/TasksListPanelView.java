package com.pengurus.crm.client.panels.center.task;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.service.ProjectService;
import com.pengurus.crm.client.service.ProjectServiceAsync;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class TasksListPanelView extends TasksListPanel {

	protected void initPanel() {
		tasksList = new ModelList();
		add(tasksList);
	}
	
	protected void initPanel(int height, int width) {
		tasksList = new ModelList(height, width);
		add(tasksList);
	}

	@Override
	protected GridCellRenderer<TaskModel> getButtonRenderer() {
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
								AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>() {

									public void onFailure(Throwable t) {
										MessageBox mb = new MessageBox();
										mb.setMessage("Server Error");
										mb.show();
									}

									@Override
									public void onSuccess(ProjectDTO projectDTO) {
										TaskPanel taskPanel = new TaskPanel(
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
				b.setToolTip("Click to see");
				buttonBar.add(b);
				return buttonBar;
			}
		};
		return buttonRenderer;
	}
}
