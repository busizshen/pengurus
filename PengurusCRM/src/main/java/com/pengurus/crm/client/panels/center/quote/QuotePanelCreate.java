package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEdit;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class QuotePanelCreate extends QuotePanel {

	public QuotePanelCreate() {
		super();
	}

	/*
	 * 
	 * @Override protected void getDescriptionPanel() { descriptionPanel = new
	 * DescriptionPanelEdit(""); quoteView.add(descriptionPanel);
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
	 * 
	 * @Override protected void getClientPanel(final QuoteView quoteView) {
	 * updateClientPanel(quoteView); if (clientPanel != null) {
	 * quoteView.add(clientPanel.getInfoPanel()); } }
	 */
	@Override
	protected void addButtonPanel(HorizontalPanel hp2) {
		HorizontalPanel vp = new HorizontalPanel();
		Button b = new Button("Create", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				quoteDTO.setDescription(descriptionPanel.getDescription());
				quoteDTO.setSupervisor(workerPanel.getChosenWorker());
				quoteDTO.setClient(clientPanel.getChosenClient());
				if (quoteDTO.check()) {
					AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

						public void onFailure(Throwable t) {

						}

						@Override
						public void onSuccess(QuoteDTO quoteDTO) {

							QuotePanelEdit qp = new QuotePanelEdit(quoteDTO);
							qp.setAsMain();
						}
					};
					QuoteServiceAsync service = (QuoteServiceAsync) GWT
							.create(QuoteService.class);
					service.createQuote(quoteDTO, callback);
				} else {
					MessageBox mb = new MessageBox();
					mb.setMessage("Please choose Client and Supervisor");
					mb.show();
				}

			}
		});
		vp.add(b);
		b = new Button("Cancel", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				MainWindow.addCenterPanel(new HorizontalPanel());
			}
		});
		vp.add(b);
		hp2.add(vp);

	}

	/*
	 * @Override protected void getJobsPanel(QuoteView quoteView) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	protected void addSupervisorPanel(VerticalPanel vpPanel) {
		workerPanel = new WorkerPanelEdit(quoteDTO.getSupervisor());
		workerPanel.setHeading("Supervisor");
		vpPanel.add(workerPanel);
	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelEdit(quoteDTO.getClient());
		clientPanel.setHeading("Client");
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(VerticalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelEdit();
		descriptionPanel.setWidth(300);
		vpPanel.add(descriptionPanel);
	}
	

	@Override
	protected  void addStatusPanel(VerticalPanel vp1){
		
	}
}
