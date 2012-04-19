package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.Label;
import com.pengurus.crm.client.panels.center.user.create.UserPreviewPanel;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class UserPanel extends FieldSet {


	protected UserDTO userDTO;
	protected 	Label name ;
	public UserPanel(UserDTO userDTO, String heading) {
		this.userDTO = userDTO;
		setHeading(heading);		
		setWidth(500);
		setAutoHeight(true);
		
	}
	protected void initPanel(){
		HorizontalPanel hp = new HorizontalPanel();
		
		name = new Label(userDTO.getFullName());
		
		hp.add(name);
				
		Button b = new Button("PREVIEW", new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				  UserPreviewPanel userPanel = new UserPreviewPanel(userDTO);
				  Window window = new Window();
				  window.add(userPanel);
				  window.setAutoHeight(true);
				  window.setAutoWidth(true);
				  window.show();
			}
		});
		hp.setSpacing(10);
		hp.add(b);
		
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
