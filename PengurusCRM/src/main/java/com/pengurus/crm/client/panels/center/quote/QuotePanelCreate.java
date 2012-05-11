package com.pengurus.crm.client.panels.center.quote;

import java.util.HashSet;
import java.util.Set;

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
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanelEditByRoles;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class QuotePanelCreate extends QuotePanel {

	public QuotePanelCreate() {
		super();
		setHeight(350);
	}

	@Override
	protected void addButtonPanel(HorizontalPanel hp2) {
		HorizontalPanel vp = new HorizontalPanel();
		Button b = new Button(myConstants.Create(), new SelectionListener<ButtonEvent>() {
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
					mb.setMessage(myMessages.PleaseChoose(myConstants.Client(),myConstants.Supervisor()));
					mb.show();
				}

			}
		});
		vp.add(b);
		b = new Button(myConstants.Cancel(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				MainWindow.addCenterPanel(new HorizontalPanel());
			}
		});
		vp.add(b);
		hp2.add(vp);

	}

	@Override
	protected void addSupervisorPanel(VerticalPanel vpPanel) {
		Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
		roles.add(UserRoleDTO.ROLE_EXECUTIVE);
		workerPanel = new WorkerPanelEditByRoles(quoteDTO.getSupervisor(),myConstants.Supervisor(),roles);
		workerPanel.setHeading(myConstants.Supervisor());
		vpPanel.add(workerPanel);
	}

	@Override
	protected void addClientPanel(VerticalPanel vpPanel) {
		clientPanel = new ClientPanelEdit(quoteDTO.getClient(),myConstants.Client());
		clientPanel.setHeading(myConstants.Client());
		vpPanel.add(clientPanel);
	}

	@Override
	protected void addDescriptionPanel(HorizontalPanel vpPanel) {
		descriptionPanel = new DescriptionPanelEdit(50,450);
		vpPanel.add(descriptionPanel);
	}
	

	@Override
	protected  void addStatusPanel(VerticalPanel vp1){
		
	}

	@Override
	protected void getJobsPanel(VerticalPanel vp0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addFilesPanel(HorizontalPanel hp0) {

	}
}
