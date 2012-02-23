package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.QuoteStatusPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelAttached;
import com.pengurus.crm.client.panels.center.user.client.ClientPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanel {
	protected QuoteDTO quoteDTO;//już jest w postaci pełnej 
	public QuotePanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
	}
	
	public void getPanel(){
		QuoteView qv = new QuoteView();
		MainWindow.addWidgetCenterPanel(qv);	
	}
	
	class QuoteView extends ContentPanel {
		
		@Override  
		protected void onRender(Element parent, int index) {  
		    super.onRender(parent, index);  
		    setLayout(new FlowLayout(10));  
		    addEditionPanel(this);
		    setHeading("Quote Panel");
		    Listener<DomEvent> listenerGenerateProject = new Listener<DomEvent>(){
				@Override
				public void handleEvent(DomEvent be) {
					// TODO Auto-generated method stub
					
			}};
		    QuoteStatusPanel statusPanel = new QuoteStatusPanel(quoteDTO.getStatus(), quoteDTO.changeStatus(quoteDTO),listenerGenerateProject);
		    add(statusPanel);
		    getClientPanel(this);
		    getSupervisorPanel(this);
		    getJobsPanel(this);
		    getDescriptionPanel(this); 
		}


	}
	protected void getDescriptionPanel(QuoteView quoteView) {
		DescriptionPanel descriptionPanel = new DescriptionPanel(quoteDTO.getDescription());
	    quoteView.add(descriptionPanel);
	}

	protected void getJobsPanel(QuoteView quoteView) {
		if(quoteDTO.getJobs() != null){
	    	JobsListPanelAttached jobsList = new JobsListPanelAttached(quoteDTO.getJobs());
	    	quoteView.add(jobsList.getPanel());
	    }
	}

	protected void getSupervisorPanel(QuoteView quoteView) {
		if(quoteDTO.getSupervisor() != null){
	    	WorkerPanel workerPanel = new WorkerPanel(quoteDTO.getSupervisor());
	    	quoteView.add(workerPanel.getInfoPanel());
	    }
	}

	protected void getClientPanel(QuoteView quoteView) {
		if(quoteDTO.getClient() != null){
	    	ClientPanel clientPanel = new ClientPanel(quoteDTO.getClient());
	    	quoteView.add(clientPanel.getInfoPanel());
		}
	}
	protected void addEditionPanel(QuoteView quoteView) {
		//sprawdzenie ról
		Button b = new Button("Edit",new SelectionListener<ButtonEvent>() {  
	          @Override  
	          public void componentSelected(ButtonEvent ce) {
	              QuotePanelEdit qp = new QuotePanelEdit(quoteDTO);
	              qp.getPanel();
	              
	          }  
	        });
		quoteView.add(b);
	}
}
