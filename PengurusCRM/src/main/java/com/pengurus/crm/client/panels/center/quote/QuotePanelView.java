package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanel;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelInput;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelOutput;
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
			/*Image img = new Image(
					"resources/images/default/icons/preview.png");
			img.setSize("20px", "30px");*/
			Button b = new Button(myConstants.Edit(), new SelectionListener<ButtonEvent>() {
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
		workerPanel = new WorkerPanelView(quoteDTO.getSupervisor(),
				myConstants.Supervisor());
		workerPanel.setHeading(myConstants.Supervisor());
		vpPanel.add(workerPanel);

	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelView(quoteDTO.getClient(), myConstants.Client());
		clientPanel.setHeading(myConstants.Client());
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(HorizontalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelView(quoteDTO.getDescription(),
				50, 550);
		descriptionPanel.setStyleAttribute("margin-right", "250px");
		vpPanel.add(descriptionPanel);

	}

	protected void getJobsPanel(VerticalPanel vp0) {
		jobsList = new JobsListPanelQuoteView(quoteDTO);
		vp0.add(jobsList);
	}

	@Override
	protected void addFilesPanel(HorizontalPanel hp0) {
		FilesPanel filesPanelIn = new FilesPanelInput(quoteDTO.getId(),
				new Long(0), new Long(0), true, true);
		filesPanelIn.setStyleAttribute("margin-right", "30px");
		hp0.add(filesPanelIn);
		FilesPanel filesPanelOut = new FilesPanelOutput(quoteDTO.getId(),
				new Long(0), new Long(0), true, true);
		hp0.add(filesPanelOut);
	}

}
