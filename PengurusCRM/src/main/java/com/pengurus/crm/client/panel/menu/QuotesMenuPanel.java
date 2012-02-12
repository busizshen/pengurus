package com.pengurus.crm.client.panel.menu;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.center.quote.QuotePanel;
import com.pengurus.crm.client.center.quote.QuotePanelLastEdited;

public class QuotesMenuPanel extends TabMenuPanel {

	public ContentPanel getPanel() {
		panel = new ContentPanel();  
	    panel.setAnimCollapse(false);  
	    panel.setHeading("QUOTES");
	    panel.setBodyStyleName("pad-text");  
	    Button b = new Button("Last Edited");
	    b.addSelectionListener(new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				QuotePanel ql = new QuotePanelLastEdited();
			}
	    });
        panel.add(b);
	    
		return panel;
	}

}
