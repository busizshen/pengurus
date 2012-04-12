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
		this.quoteDTO = new QuoteDTO();
		getInfoPanel();
	}

	public QuotePanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
		getInfoPanel();
		getJobsPanel();
	}

	/*
	 * class QuoteView extends ContentPanel {
	 */
	/*
	 * public QuoteView() { setLayout(new FlowLayout(10));
	 * addEditionPanel(this); setHeading("Quote Panel"); getInfoPanel();
	 * getStatusPanel(this); getClientPanel(this); getSupervisorPanel(this);
	 * getJobsPanel(this); getDescriptionPanel(this); }
	 */

	private void getInfoPanel() {
		FormPanel simple = new FormPanel();
		simple.setFrame(false);
		simple.setHeaderVisible(false);
		simple.setBorders(true);
		simple.setAutoHeight(true);
		simple.setAutoWidth(true);

		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);

		// pierwsza kolumna
		VerticalPanel vp1 = new VerticalPanel();
		vp1.setSpacing(5);

		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.setSpacing(5);
		addButtonPanel(hp2);

		vp1.add(hp2);

		addStatusPanel(vp1);

		addSupervisorPanel(vp1);

		addClientPanel(vp1);

		hp.add(vp1);

		// druga kolumna
		VerticalPanel vp2 = new VerticalPanel();
		vp1.setSpacing(5);
		addDescriptionPanel(vp2);
		hp.add(vp2);
		simple.add(hp);
		add(simple);
	}

	protected abstract void addDescriptionPanel(VerticalPanel vpPanel);

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

	protected void getJobsPanel() {
	}

}
