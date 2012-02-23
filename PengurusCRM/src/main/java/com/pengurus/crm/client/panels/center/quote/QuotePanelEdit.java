package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanelEdit extends QuotePanel {
	public QuotePanelEdit(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	public void getPanel() {
		QuoteView qv = new QuoteView();
		MainWindow.addWidgetCenterPanel(qv);
	}

	@Override
	protected void getDescriptionPanel(QuoteView quoteView) {
		DescriptionPanelEdit descriptionPanel = new DescriptionPanelEdit(
				quoteDTO.getDescription());
		quoteView.add(descriptionPanel);
	}
	
	protected void getClientPanel(QuoteView quoteView) {
		if(quoteDTO.getClient() != null){
	    	ClientPanelEdit clientPanel = new ClientPanelEdit(quoteDTO.getClient());
	    	quoteView.add(clientPanel.getInfoPanel());
		}
	}

	@Override
	protected void addEditionPanel(QuoteView quoteView) {
		Listener<DomEvent> listenerGenerateProject = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
				// TODO Auto-generated method stub
				
		}};
		Button b = new Button("Update",new SelectionListener<ButtonEvent>() {  
	          @Override  
	          public void componentSelected(ButtonEvent ce) {
	        	  quoteDTO.update(quoteDTO);
	              QuotePanel qp = new QuotePanel(quoteDTO);
	              qp.getPanel();
	              
	          }  
	        });
		quoteView.add(b);
		b = new Button("Cancel",new SelectionListener<ButtonEvent>() {  
	          @Override  
	          public void componentSelected(ButtonEvent ce) {
	        	  //pobierz starą wersję
	              QuotePanel qp = new QuotePanel(quoteDTO);
	              qp.getPanel();
	              
	          }  
	        });
		quoteView.add(b);
		
	}

}
