package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.pengurus.crm.client.panels.center.DescriptionPanel;

public abstract class UserPanel {

	protected abstract String getUserHeading();
	protected abstract String getUsername();
	protected abstract String getUserDescription();
	protected abstract  void addEditionPanel(UserViewInfo userViewInfo);
	
	public class UserViewInfo extends ContentPanel {
		
		public UserViewInfo(){
			this.setCollapsible(true);  
		    this.setAnimCollapse(true);
		    setHeading(getUserHeading());
		    setLayout(new RowLayout(Orientation.VERTICAL));
		}

		@Override  
		protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index); 
		    
		    Label login = new Label();
		    login.setText(getUsername());
		    login.setBorders(true);
		    login.show();
		    add(login, new RowData(-1, -1, new Margins(4)));
		    DescriptionPanel descr = new DescriptionPanel(getUserDescription());
		    descr.setNonEditable();
		    add(descr, new RowData(-1, 1, new Margins(4)));
		    addEditionPanel(this);
		    
		}

	}

}
