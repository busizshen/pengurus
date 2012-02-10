package com.pengurus.crm.client.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.center.QuotePanel;
import com.pengurus.crm.client.service.CurrentSessionService;
import com.pengurus.crm.client.service.CurrentSessionServiceAsync;
import com.pengurus.crm.shared.User;

public class QuotesMenuPanel extends TabMenuPanel {

	public ContentPanel getPanel() {
		panel = new ContentPanel();  
	    panel.setAnimCollapse(false);  
	    panel.setHeading("QUOTES");
	    panel.setBodyStyleName("pad-text");  
	    
		AsyncCallback<User> callback = new AsyncCallback<User>() {

			public void onFailure(Throwable t) {
				MessageBox.info("Message", t.toString(), null);
			}

			public void onSuccess(User result) {
				MessageBox.info("Message", result.getUsername(), null);
			}
		};
		CurrentSessionServiceAsync service = (CurrentSessionServiceAsync) GWT
				.create(CurrentSessionService.class);
		service.getCurrentUser(callback);
		
	    Button b = new Button("Last Edited");
	    b.addSelectionListener(new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
			   QuotePanel ql = new QuotePanel();
				   ql.LastEdited();
			}
	    });
        panel.add(b);
	    
		return panel;
	}

}
