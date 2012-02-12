package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanel {
	private QuoteDTO quoteDTO;
	public QuotePanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
		QuoteView qv = new QuoteView();
		MainWindow.addWidgetCenterPanel(qv);
	}
	
	class QuoteView extends LayoutContainer{
		
		@Override  
		protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index);  
		    setLayout(new FlowLayout(10));  
		    ContentPanel cp = new ContentPanel();
		    cp.setHeading(quoteDTO.getDescription());
		    add(cp);
		}
	}
}
