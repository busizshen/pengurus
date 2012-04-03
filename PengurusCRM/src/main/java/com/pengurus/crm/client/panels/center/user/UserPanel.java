package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.shared.dto.UserDTO;

public abstract class UserPanel {

	protected abstract String getUserHeading();
	protected abstract String getUserDescription();
	protected UserViewInfo userInfoPanel;
	protected UserDTO userDTO;
	private UserDTO getUserDTO(){
		return this.userDTO;
	}
	public UserPanel(UserDTO userDTO){
		this.userDTO = userDTO;
	}
	public UserViewInfo getInfoPanel() {
		return userInfoPanel;
	}
	
	public class UserViewInfo extends ContentPanel {
		
		HorizontalPanel hp;

		public UserViewInfo(){
			hp = new HorizontalPanel();
			hp.setSpacing(10);
			this.setCollapsible(true);  
		    this.setAnimCollapse(true);
		    this.collapse();
		    hp.setAutoWidth(true);
		    hp.setAutoHeight(true);
		    setHeading(getUserHeading());
		    if(getUserDTO() != null){	    	
		    	hp.add(getInfoForm());
		    	DescriptionPanel descr = new DescriptionPanel(getUserDescription());
		    	hp.add(descr);
		    	descr.setWidth(300);
		    }
		    add(hp);
		   }

	}

	public abstract Widget getInfoForm();

}
