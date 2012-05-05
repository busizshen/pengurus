package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
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
		setHeading("Quote");
		this.quoteDTO = new QuoteDTO();
		getInfoPanel();
	}

	public QuotePanel(QuoteDTO quoteDTO) {
		setHeading("Quote");
		this.quoteDTO = quoteDTO;
		getInfoPanel();
	}

	private void getInfoPanel() {

		VerticalPanel vp0 = new VerticalPanel();
		HorizontalPanel hp0 = new HorizontalPanel();
		addDescriptionPanel(hp0);
		addButtonPanel(hp0);
		vp0.add(hp0);
		VerticalPanel vp = new VerticalPanel();
		addSupervisorPanel(vp);
		addClientPanel(vp);
		vp0.add(vp);
		getJobsPanel(vp0);
		addFilesPanel(vp0);

		add(vp0);

	}

	protected abstract void addDescriptionPanel(HorizontalPanel hp0);

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

	protected abstract void addFilesPanel(VerticalPanel vp0);
}
