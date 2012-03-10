package com.pengurus.crm.client.panels.center.user.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanelEdit extends ClientPanel {

	private Listener<DomEvent> listenerChangeClient;
	ClientsListPanel cl;

	public ClientPanelEdit(ClientDTO client, Listener<DomEvent> listener) {
		super(client);
		this.listenerChangeClient = listener;
		userInfoPanel = new UserViewInfo();
		addEditionPanel(userInfoPanel);
	}

	public ClientPanelEdit(Listener<DomEvent> listener) {
		super();
		this.listenerChangeClient = listener;
		addEditionPanel(userInfoPanel);
	}

	protected void addEditionPanel(UserViewInfo userViewInfo) {
		final Window w = new Window();
		Listener<DomEvent> listenerCloseTab = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				w.hide();
			}
		};
		cl = new ClientsListPanel(this.listenerChangeClient, listenerCloseTab);
		w.add(cl.getPanel());
		w.setAutoWidth(true);
		w.setAutoHide(true);
		w.setEnabled(true);
		w.setHeading("ChangeClient");
		w.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		w.add(b2);
		Button b = new Button("Change Client",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						w.show();

					}
				});
		userViewInfo.add(b);
	}

	@Override
	public ClientDTO getChosenClient() {
		return cl.getChosenClient();
	}


}
