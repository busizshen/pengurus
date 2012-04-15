package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.job.JobsListPanelQuoteView;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanelView extends QuotePanel {
	public QuotePanelView(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hpPanel) {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Edit", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					QuotePanelEdit qp = new QuotePanelEdit(quoteDTO);
					qp.setAsMain();

				}
			});
			hpPanel.add(b);
		}
	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vpPanel) {
		workerPanel = new WorkerPanelView(quoteDTO.getSupervisor(),"Supervisor");
		workerPanel.setHeading("Supervisor");
		vpPanel.add(workerPanel);
		
	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelView(quoteDTO.getClient(),"Client");
		clientPanel.setHeading("Client");
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(VerticalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelView(quoteDTO.getDescription());
		descriptionPanel.setWidth(300);
		vpPanel.add(descriptionPanel);
	
	}

	protected void getJobsPanel() {
		jobsList = new JobsListPanelQuoteView(quoteDTO);
		add(jobsList.getPanel());
	}


}
