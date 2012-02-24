package com.pengurus.crm.client.panels.center.user.worker;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.WorkerModel;
import com.pengurus.crm.client.panels.center.user.UsersListPanel;
import com.pengurus.crm.client.service.UserService;
import com.pengurus.crm.client.service.UserServiceAsync;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkersListPanel extends UsersListPanel<WorkerModel> {

	WorkerDTO chosen;
	private Listener<DomEvent> listenerChangeWorker;
	private Listener<DomEvent> listenerCloseTab;
	public WorkersListPanel(Listener<DomEvent> listenerChangeWorker,
			Listener<DomEvent> listenerCloseTab) {
		this.listenerChangeWorker = listenerChangeWorker;
		this.listenerCloseTab = listenerCloseTab;
	}

	@Override
	protected GridCellRenderer getButtonRenderer() {
		GridCellRenderer<WorkerModel> buttonRenderer = new GridCellRenderer<WorkerModel>() {

			private boolean init;

			public Object render(final WorkerModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<WorkerModel> store, Grid<WorkerModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<WorkerModel>>() {

								public void handleEvent(
										GridEvent<WorkerModel> be) {
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

				Button b = new Button("Choose",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								chosen = model.getWorkerDTO();
							}
						});

				b.addListener(Events.OnClick, listenerCloseTab);
				b.addListener(Events.OnClick, listenerChangeWorker);
				b.setToolTip("Click to see");

				return b;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ListStore<WorkerModel> getList() {
		final ListStore<WorkerModel> list = new ListStore<WorkerModel>();
		AsyncCallback<Set<UserDTO>> callback = new AsyncCallback<Set<UserDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<UserDTO> result) {
				for (UserDTO q : result) {
					list.add(new WorkerModel((WorkerDTO)q));
				}
			}
		};
		UserServiceAsync service = (UserServiceAsync) GWT
				.create(UserService.class);
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMNAGER);
		service.getUsersByRole(roles, callback);

		return list;
	}

	public WorkerDTO getChosenWorker() {
		return chosen;
	}

}
