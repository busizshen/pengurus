package com.pengurus.crm.client.panels.center.task;

import java.util.Set;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.TaskModel;
import com.pengurus.crm.client.service.TaskService;
import com.pengurus.crm.client.service.TaskServiceAsync;
import com.pengurus.crm.shared.dto.TaskDTO;


public class TasksListPanelAll extends TasksListPanel {

	public TasksListPanelAll() {
		super();
		initPanel();
	}

	
	protected void initPanel() {
		ml = new ModelList();
		add(ml);
	}

	@Override
	protected ListStore<TaskModel> getList() {
		final ListStore<TaskModel> list = new ListStore<TaskModel>();
		AsyncCallback<Set<TaskDTO> > callback = new AsyncCallback<Set<TaskDTO> >() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error");
				mb.show();
			}

			public void onSuccess(Set<TaskDTO> result) {
				for(TaskDTO q: result){
					list.add(new TaskModel(q));
				}
			}
		};
		TaskServiceAsync service = (TaskServiceAsync) GWT
				.create(TaskService.class);
		service.getTasks(callback);

		return list;
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
						/*		get Project by task id
								TaskPanel taskPanel = new TaskPanel(
										model.getTaskDTO(), projectDTO);
								taskPanel.setAsMain();*/
								MessageBox mb = new MessageBox();
								mb.setMessage("Potrzebna funkcja get project by task");
								mb.show();
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
