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
import com.pengurus.crm.client.panels.center.job.JobsListPanelQuoteEdit;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByRoles;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class QuotePanelEdit extends QuotePanel {
	public QuotePanelEdit(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hpPanel) {
		if (AuthorizationManager.hasExecutiveAccess()) {
			HorizontalPanel hp = new HorizontalPanel();
			Button b = new Button("Update",
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
			hp.add(b);
			b = new Button("Delete", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					quoteDTO.delete();
					MainWindow.addCenterPanel(new ContentPanel());

				}
			});
			hp.add(b);
			b = new Button("Cancel", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// pobierz starą wersję
					QuotePanelView qp = new QuotePanelView(quoteDTO);
					qp.setAsMain();
				}
			});
			hp.add(b);
			hpPanel.add(hp);
		}
	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vpPanel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_PROJECTMANAGER);
		workerPanel = new WorkerPanelEditByRoles(quoteDTO.getSupervisor(),
				"Supervisor", roles);
		workerPanel.setHeading("Supervisor");
		vpPanel.add(workerPanel);
	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelEdit(quoteDTO.getClient(), "Client");
		clientPanel.setHeading("Client");
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(VerticalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelEdit(quoteDTO.getDescription());
		descriptionPanel.setWidth(300);
		vpPanel.add(descriptionPanel);
	}

	/*
	 * @Override protected void getDescriptionPanel(QuoteView quoteView) {
	 * descriptionPanel = new DescriptionPanelEdit(quoteDTO.getDescription());
	 * quoteView.add(descriptionPanel); }
	 * 
	 * @Override protected void getClientPanel(final QuoteView quoteView) {
	 * updateClientPanel(quoteView); if (clientPanel != null) {
	 * quoteView.add(clientPanel.getInfoPanel()); } }
	 * 
	 * protected void updateClientPanel(final QuoteView quoteView) {
	 * Listener<DomEvent> listener = new Listener<DomEvent>() {
	 * 
	 * @Override public void handleEvent(DomEvent be) {
	 * quoteDTO.setClient(clientPanel.getChosenClient()); getPanel();// do
	 * zmiany jak zrobić żeby tylko jeden element się // zmieniał a nie trzeb
	 * było ładować całości na nowo } }; if (quoteDTO.getClient() != null) {
	 * clientPanel = new ClientPanelEdit(quoteDTO.getClient(), listener); } else
	 * clientPanel = new ClientPanelEdit(listener); }
	 * 
	 * @Override protected void addEditionPanel(QuoteView quoteView) { if
	 * (AuthorizationManager.hasExecutiveAccess()) { HorizontalPanel vp = new
	 * HorizontalPanel(); Button b = new Button("Update", new
	 * SelectionListener<ButtonEvent>() {
	 * 
	 * @Override public void componentSelected(ButtonEvent ce) {
	 * quoteDTO.setDescription(descriptionPanel .getDescription());
	 * quoteDTO.update(); QuotePanelView qp = new QuotePanelView(quoteDTO);
	 * qp.getPanel();
	 * 
	 * } }); vp.add(b); b = new Button("Delete", new
	 * SelectionListener<ButtonEvent>() {
	 * 
	 * @Override public void componentSelected(ButtonEvent ce) {
	 * quoteDTO.delete(); MainWindow.addCenterPanel(new ContentPanel());
	 * 
	 * } }); vp.add(b); b = new Button("Cancel", new
	 * SelectionListener<ButtonEvent>() {
	 * 
	 * @Override public void componentSelected(ButtonEvent ce) { // pobierz
	 * starą wersję QuotePanelView qp = new QuotePanelView(quoteDTO);
	 * qp.getPanel();
	 * 
	 * } }); vp.add(b); quoteView.add(vp); }
	 * 
	 * }
	 * 
	 * @Override protected void getSupervisorPanel(final QuoteView quoteView) {
	 * updateSupervisorPanel(quoteView); if (workerPanel != null) {
	 * quoteView.add(workerPanel.getInfoPanel()); } }
	 * 
	 * protected void updateSupervisorPanel(QuoteView quoteView) {
	 * Listener<DomEvent> listener = new Listener<DomEvent>() {
	 * 
	 * @Override public void handleEvent(DomEvent be) {
	 * quoteDTO.setSupervisor(workerPanel.getChosenWorker()); getPanel();// do
	 * zmiany jak zrobić żeby tylko jeden element się // zmieniał a nie trzeb
	 * było ładować całości na nowo } }; if (quoteDTO.getSupervisor() != null) {
	 * workerPanel = new WorkerPanelEdit(quoteDTO.getSupervisor(), listener); }
	 * else workerPanel = new WorkerPanelEdit(listener); }
	 */
	@Override
	protected void getJobsPanel() {
		if (quoteDTO.getJobs() != null) {
			jobsList = new JobsListPanelQuoteEdit(quoteDTO);
			add(jobsList.getPanel());
		}

	}

}
