package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class UserPanel extends FormPanel {


	private String getUserDescription(){
		if(userDTO != null){
			return userDTO.getDescription();
		}
		return "";
	}
	protected UserDTO userDTO;
	protected DescriptionPanel descriptionPanel;
	
	public UserPanel(UserDTO userDTO, String heading) {
		this.userDTO = userDTO;
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);
		hp.setAutoWidth(true);
		hp.setAutoHeight(true);
		setHeading(heading);
		hp.add(getInfoForm());
		descriptionPanel = new DescriptionPanelView(getUserDescription());
		hp.add(descriptionPanel);
		descriptionPanel.setWidth(300);
		add(hp);
	}

	public abstract Widget getInfoForm();

}
