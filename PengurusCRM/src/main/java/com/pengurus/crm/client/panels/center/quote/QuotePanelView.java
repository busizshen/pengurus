package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanelQuoteView;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelView;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelView;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanelView extends QuotePanel {
	public QuotePanelView(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
	}

	public void getPanel() {
		QuoteView qv = new QuoteView();
		MainWindow.addCenterPanel(qv);
	}

	@Override
	protected void getDescriptionPanel(QuoteView quoteView) {
		descriptionPanel = new DescriptionPanel(quoteDTO.getDescription());
		quoteView.add(descriptionPanel);
	}

	@Override
	protected void getJobsPanel(QuoteView quoteView) {
    	if (quoteDTO.getJobs() != null) {
			jobsList = new JobsListPanelQuoteView(quoteDTO);
			quoteView.add(jobsList.getPanel());
		}
	}

	@Override
	protected void getSupervisorPanel(QuoteView quoteView) {
		if (quoteDTO.getSupervisor() != null) {
			workerPanel = new WorkerPanelView(quoteDTO.getSupervisor());
			quoteView.add(workerPanel.getInfoPanel());
		}
	}

	@Override
	protected void getClientPanel(QuoteView quoteView) {
		if (quoteDTO.getClient() != null) {
			clientPanel = new ClientPanelView(quoteDTO.getClient());
			quoteView.add(clientPanel.getInfoPanel());
		}
	}

	@Override
	protected void addEditionPanel(QuoteView quoteView) {
		if (AuthorizationManager.hasExecutiveAccess()) {
			Button b = new Button("Edit", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					QuotePanelEdit qp = new QuotePanelEdit(quoteDTO);
					qp.getPanel();

				}
			});
			quoteView.add(b);
		}
	}

}
