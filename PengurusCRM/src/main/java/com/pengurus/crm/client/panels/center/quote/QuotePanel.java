package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanel;
import com.pengurus.crm.client.panels.center.status.QuoteStatusPanel;
import com.pengurus.crm.client.panels.center.user.client.ClientPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class QuotePanel extends MainPanel {
	protected QuoteDTO quoteDTO;// już jest w postaci pełnej
	DescriptionPanel descriptionPanel;
	JobsListPanel jobsList;
	WorkerPanel workerPanel;
	ClientPanel clientPanel;

	public QuotePanel() {
		super(900);
		setHeading(myConstants.Quote());
		this.quoteDTO = new QuoteDTO();
		getInfoPanel();
	}

	public QuotePanel(QuoteDTO quoteDTO) {
		super(900);
		setHeading(myConstants.Quote());
		this.quoteDTO = quoteDTO;
		getInfoPanel();
	}

	private void getInfoPanel() {

		VerticalPanel mainVerticalPanel = new VerticalPanel();
		mainVerticalPanel.setSpacing(15);
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		HorizontalPanel topHorizontalPanel = new HorizontalPanel();
		addDescriptionPanel(topHorizontalPanel);
		addButtonPanel(topHorizontalPanel);
		
		mainVerticalPanel.add(topHorizontalPanel);
		
		
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		addStatusPanel(mainVerticalPanel);
		
		VerticalPanel personPanel = new VerticalPanel();
		addSupervisorPanel(personPanel);
		addClientPanel(personPanel);
		
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		mainVerticalPanel.add(personPanel);

		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		getJobsPanel(mainVerticalPanel);
		
		HorizontalPanel filesPanel = new HorizontalPanel();
		addFilesPanel(filesPanel);

		mainVerticalPanel.add(filesPanel);
		add(mainVerticalPanel);

	}

	protected abstract void addDescriptionPanel(HorizontalPanel hpPanel);

	protected abstract void addClientPanel(VerticalPanel vpPanel);

	protected abstract void addSupervisorPanel(VerticalPanel vpPanel);

	protected abstract void addButtonPanel(HorizontalPanel hpPanel);

	protected void addStatusPanel(VerticalPanel vp1) {
		Listener<DomEvent> listenerGenerateProject = new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				ProjectDTO projectDTO = new ProjectDTO(quoteDTO);
				projectDTO.create(projectDTO);

			}
		};
		QuoteStatusPanel statusPanel = new QuoteStatusPanel(
				quoteDTO.getStatus(), quoteDTO.changeStatus(quoteDTO),
				listenerGenerateProject, quoteDTO.backStatus(quoteDTO));
		vp1.add(statusPanel);
	}

	protected abstract void getJobsPanel(VerticalPanel vp0);

	protected abstract void addFilesPanel(HorizontalPanel vp0);
}
