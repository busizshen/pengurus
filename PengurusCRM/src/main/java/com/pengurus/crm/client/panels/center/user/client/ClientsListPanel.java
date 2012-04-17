package com.pengurus.crm.client.panels.center.user.client;

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
import com.pengurus.crm.client.models.ClientModel;
import com.pengurus.crm.client.panels.center.user.BaseUsersListPanel;
import com.pengurus.crm.client.service.ClientService;
import com.pengurus.crm.client.service.ClientServiceAsync;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientsListPanel extends BaseUsersListPanel<ClientModel> {

	ClientDTO chosen;
	private Listener<DomEvent> listenerChangeClient;
	private Listener<DomEvent> listenerCloseTab;

	public ClientsListPanel(Listener<DomEvent> listenerChangeClient2,
			Listener<DomEvent> listenerCloseTab3) {
		this.listenerChangeClient = listenerChangeClient2;
		this.listenerCloseTab = listenerCloseTab3;
		modelList = new ModelList();
	}

	@Override
	protected GridCellRenderer<ClientModel> getButtonRenderer() {
		GridCellRenderer<ClientModel> buttonRenderer = new GridCellRenderer<ClientModel>() {

			private boolean init;

			public Object render(final ClientModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<ClientModel> store, Grid<ClientModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<ClientModel>>() {

								public void handleEvent(
										GridEvent<ClientModel> be) {
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
								chosen = model.getClientDTO();
							}
						});

				b.addListener(Events.OnClick, listenerCloseTab);
				b.addListener(Events.OnClick, listenerChangeClient);
				b.setToolTip("Click to see");

				return b;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		return "Clients list";
	}

	@Override
	protected ListStore<ClientModel> getList() {
		final ListStore<ClientModel> list = new ListStore<ClientModel>();
		AsyncCallback<Set<ClientDTO>> callback = new AsyncCallback<Set<ClientDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<ClientDTO> result) {
				for (ClientDTO q : result) {
					list.add(new ClientModel(q));
				}
			}
		};
		ClientServiceAsync service = (ClientServiceAsync) GWT
				.create(ClientService.class);
		service.getClients(callback);

		return list;
	}

	public ClientDTO getChosenClient() {
		return chosen;
	}

}
