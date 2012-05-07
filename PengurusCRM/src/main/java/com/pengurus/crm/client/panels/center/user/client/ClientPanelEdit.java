package com.pengurus.crm.client.panels.center.user.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.shared.dto.ClientDTO;

public class ClientPanelEdit extends ClientPanel {

	ClientsListPanel cl;

	public ClientPanelEdit(ClientDTO client,String heading) {
		super(client,heading);
	}

	public ClientPanelEdit() {
		super();
	}

	protected void addEditionPanel(HorizontalPanel hp) {
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
				userDTO = getChosenClient();
				refresh(userDTO);
			}
		};
		cl = new ClientsListPanel(listenerChangeClient, listenerCloseTab);
		window.add(cl.getModelList());
		window.setAutoWidth(true);
		window.setAutoHide(true);
		window.setEnabled(true);
		window.setHeading("Select Client");
		window.setClosable(false);
		Button b2 = new Button("Cancel");
		b2.addListener(Events.OnClick, listenerCloseTab);
		window.add(b2);
		Button b = new Button("Select",
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						window.show();

					}
				});
		hp.add(b);
	}

	@Override
	public ClientDTO getChosenClient() {
		return cl.getChosenClient();
	}


}
