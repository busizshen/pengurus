package com.pengurus.crm.client.panels.center.quote;

import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanel;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelInput;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelOutput;
import com.pengurus.crm.client.panels.center.job.JobsListPanelQuoteEdit;
import com.pengurus.crm.client.panels.center.job.JobsListPanelQuoteView;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByRoles;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.StatusQuoteDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class QuotePanelEdit extends QuotePanel {
	public QuotePanelEdit(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hpPanel) {
		if (AuthorizationManager.hasExecutiveAccess()) {
			HorizontalPanel hp = new HorizontalPanel();
			Button buttonUpdate = new Button(myConstants.Update(),
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							quoteDTO.setDescription(descriptionPanel
									.getDescription());
							if (workerPanel.getChosenWorker() != null)
								quoteDTO.setSupervisor(workerPanel
										.getChosenWorker());
							if (clientPanel.getChosenClient() != null)
								quoteDTO.setClient(clientPanel
										.getChosenClient());
							quoteDTO.update();
							QuotePanelView qp = new QuotePanelView(quoteDTO);
							qp.setAsMain();

						}
					});
			hp.add(buttonUpdate);
			Button buttonCancel = new Button(myConstants.Delete(),
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							quoteDTO.delete();
							MainWindow.addCenterPanel(new ContentPanel());

						}
					});
			hp.add(buttonCancel);
			buttonCancel = new Button(myConstants.Cancel(),
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							QuotePanelView qp = new QuotePanelView(quoteDTO);
							qp.setAsMain();
						}
					});
			hp.add(buttonCancel);
			hpPanel.add(hp);
		}
	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vpPanel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		workerPanel = new WorkerPanelEditByRoles(quoteDTO.getSupervisor(),
				myConstants.Supervisor(), roles);
		workerPanel.setHeading(myConstants.Supervisor());
		vpPanel.add(workerPanel);
	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelEdit(quoteDTO.getClient(), myConstants.Client());
		clientPanel.setHeading(myConstants.Client());
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(HorizontalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelEdit(quoteDTO.getDescription(),
				50, 550);
		descriptionPanel.setStyleAttribute("margin-right", "200px");
		vpPanel.add(descriptionPanel);
	}

	@Override
	protected void getJobsPanel(VerticalPanel vp0) {
		if (quoteDTO.getJobs() != null) {
			if (quoteDTO.getStatus().toInt() < StatusQuoteDTO.agreed.toInt())
				jobsList = new JobsListPanelQuoteEdit(quoteDTO);
			else
				jobsList = new JobsListPanelQuoteView(quoteDTO);
			vp0.add(jobsList);
		}

	}

	protected void addFilesPanel(HorizontalPanel hp0) {
		FilesPanel filesPanelIn = new FilesPanelInput(quoteDTO.getId(),new Long(0), new Long(0), true, true);
		hp0.add(filesPanelIn);
		filesPanelIn.setStyleAttribute("margin-right", "30px");
		FilesPanel filesPanelOut = new FilesPanelOutput(quoteDTO.getId(), new Long(0), new Long(0), true, true);
		hp0.add(filesPanelOut);
	}

}
