package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
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
		
		public UserViewInfo(){
			this.setCollapsible(true);  
		    this.setAnimCollapse(true);
		    this.collapse();
		    setHeading(getUserHeading());
		    setLayout(new RowLayout(Orientation.VERTICAL));
		    if(getUserDTO() != null){
		    	Label login = new Label();
		    	login.setText(getUserDTO().getUsername());
		    	login.setBorders(true);
		    	login.show();
		    	add(login, new RowData(-1, -1, new Margins(4)));
		    	DescriptionPanel descr = new DescriptionPanel(getUserDescription());
		    	descr.setNonEditable();
		    	add(descr, new RowData(-1, 1, new Margins(4)));
		    }
		   }

	}

}
