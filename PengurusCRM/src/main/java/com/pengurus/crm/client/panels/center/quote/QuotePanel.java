package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.QuoteStatusPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelAttached;
import com.pengurus.crm.client.panels.center.user.client.ClientPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class QuotePanel {
	protected QuoteDTO quoteDTO;//już jest w postaci pełnej 
	DescriptionPanel descriptionPanel;
	JobsListPanelAttached jobsList;
	WorkerPanel workerPanel;
	ClientPanel clientPanel;
	
	public QuotePanel(){
		this.quoteDTO = new QuoteDTO();
	}
	
	public QuotePanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
	}
	
	public void getPanel(){
		QuoteView qv = new QuoteView();
		MainWindow.addWidgetCenterPanel(qv);	
	}
	
	class QuoteView extends ContentPanel {
		
		public QuoteView() { 
		    setLayout(new FlowLayout(10));  
		    addEditionPanel(this);
		    setHeading("Quote Panel");
			getStatusPanel(this);
		    getClientPanel(this);
		    getSupervisorPanel(this);
	//	    getJobsPanel(this); - blad w panelu jobów
		    getDescriptionPanel(this); 
		}


	}
	protected abstract void getDescriptionPanel(QuoteView quoteView);
	protected void getStatusPanel(QuoteView quoteView) {
	    Listener<DomEvent> listenerGenerateProject = new Listener<DomEvent>(){
			@Override
			public void handleEvent(DomEvent be) {
									
		}};
		QuoteStatusPanel statusPanel = new QuoteStatusPanel(quoteDTO.getStatus(), quoteDTO.changeStatus(quoteDTO),listenerGenerateProject);
	    quoteView.add(statusPanel);
	}

	protected abstract void getJobsPanel(QuoteView quoteView);
	protected abstract void getSupervisorPanel(QuoteView quoteView);
	protected abstract void getClientPanel(QuoteView quoteView);
	protected abstract void addEditionPanel(QuoteView quoteView);
}
