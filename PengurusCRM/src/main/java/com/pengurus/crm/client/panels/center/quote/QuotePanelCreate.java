package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.user.client.ClientPanelEdit;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEdit;
import com.pengurus.crm.shared.dto.StatusDTO;

public class QuotePanelCreate extends QuotePanel {

	public QuotePanelCreate() {
		super();
	}

	@Override
	protected void getStatusPanel(QuoteView quoteView) {	
	}
	
	@Override
	protected void getDescriptionPanel(QuoteView quoteView) {
		descriptionPanel = new DescriptionPanelEdit("");
		quoteView.add(descriptionPanel);

	}

	@Override
	protected void getJobsPanel(QuoteView quoteView) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void getSupervisorPanel(final QuoteView quoteView) {
		updateSupervisorPanel(quoteView);
		if (workerPanel != null) {
			quoteView.add(workerPanel.getInfoPanel());
		}
	}

	protected void updateSupervisorPanel(QuoteView quoteView) {
		Listener<DomEvent> listener = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				quoteDTO.setSupervisor(workerPanel.getChosenWorker());
				getPanel();// do zmiany jak zrobić żeby tylko jeden element się
							// zmieniał a nie trzeb było ładować całości na nowo
			}
		};
		if (quoteDTO.getSupervisor() != null) {
			workerPanel = new WorkerPanelEdit(quoteDTO.getSupervisor(),
					listener);
		} else
			workerPanel = new WorkerPanelEdit(listener);
	}

	@Override
	protected void getClientPanel(final QuoteView quoteView) {
		updateClientPanel(quoteView);
		if (clientPanel != null) {
			quoteView.add(clientPanel.getInfoPanel());
		}
	}

	protected void updateClientPanel(final QuoteView quoteView) {
		Listener<DomEvent> listener = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				quoteDTO.setStatus(StatusDTO.open);
				quoteDTO.setClient(clientPanel.getChosenClient());
				getPanel();// do zmiany jak zrobić żeby tylko jeden element się
							// zmieniał a nie trzeb było ładować całości na nowo
			}
		};
		if (quoteDTO.getClient() != null) {
			clientPanel = new ClientPanelEdit(quoteDTO.getClient(), listener);
		} else
			clientPanel = new ClientPanelEdit(listener);
	}

	@Override
	protected void addEditionPanel(QuoteView quoteView) {
		HorizontalPanel vp = new HorizontalPanel();
		Button b = new Button("Create", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (quoteDTO.check()) {
					quoteDTO.setDescription(descriptionPanel.getDescription());
					quoteDTO.create();
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
		quoteView.add(vp);

	}

}
