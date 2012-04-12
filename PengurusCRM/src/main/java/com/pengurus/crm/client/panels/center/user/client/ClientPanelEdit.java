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

	ClientsListPanel cl;

	public ClientPanelEdit(ClientDTO client) {
		super(client);
		addEditionPanel();
	}

	public ClientPanelEdit() {
		super();
		addEditionPanel();
	}

	protected void addEditionPanel() {
		final Window window = new Window();
		Listener<DomEvent> listenerCloseTab = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				window.hide();
			}
		};
		Listener<DomEvent> listenerChangeClient = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				clientDTO = getChosenClient();
				refresh(clientDTO);
			}
		};
		cl = new ClientsListPanel(listenerChangeClient, listenerCloseTab);
		window.add(cl.getPanel());
		window.setAutoWidth(true);
		window.setAutoHide(true);
		window.setEnabled(true);
		window.setHeading("ChangeClient");
		window.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		window.add(b2);
		Button b = new Button("Change Client",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.show();

					}
				});
		add(b);
	}

	@Override
	public ClientDTO getChosenClient() {
		return cl.getChosenClient();
	}


}
