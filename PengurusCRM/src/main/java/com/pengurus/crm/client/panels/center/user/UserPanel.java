package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.pengurus.crm.client.panels.center.user.create.UserPreviewPanel;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class UserPanel extends FieldSet {

	protected UserDTO userDTO;
	protected Anchor name;

	public UserPanel(UserDTO userDTO, String heading) {
		this.userDTO = userDTO;
		setHeading(heading);
		setWidth(400);
		setAutoHeight(true);

	}

	protected void initPanel() {
		HorizontalPanel hp = new HorizontalPanel();

		if (userDTO != null) {
			name = new Anchor(userDTO.getFullName());
			name.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					UserPreviewPanel userPanel = new UserPreviewPanel(userDTO);
					Window window = new Window();
					window.add(userPanel);
					window.setAutoHeight(true);
					window.setAutoWidth(true);
					window.show();
				}

			});
		} else {
			name = new Anchor();
		}
		hp.add(name);

		hp.setSpacing(7);

		addEditionPanel(hp);
		add(hp);

	}

	protected abstract void addEditionPanel(HorizontalPanel hp);

	public void refresh(UserDTO userDTO) {
		this.userDTO = userDTO;
		if (userDTO != null) {
			name.setText(userDTO.getFullName());
		}
	}
}
