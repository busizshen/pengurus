package com.pengurus.crm.client.panels.center.quote;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.pengurus.crm.client.MainWindow;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.job.JobsListPanel;
import com.pengurus.crm.client.panels.center.status.QuoteStatusPanel;
import com.pengurus.crm.client.panels.center.user.client.ClientPanel;
import com.pengurus.crm.client.panels.center.user.worker.WorkerPanel;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public abstract class QuotePanel {
	protected QuoteDTO quoteDTO;// już jest w postaci pełnej
	DescriptionPanel descriptionPanel;
	JobsListPanel jobsList;
	WorkerPanel workerPanel;
	ClientPanel clientPanel;

	public QuotePanel() {
		this.quoteDTO = new QuoteDTO();
	}

	public QuotePanel(QuoteDTO quoteDTO) {
		this.quoteDTO = quoteDTO;
	}

	public void getPanel() {
		QuoteView qv = new QuoteView();
		MainWindow.addCenterPanel(qv);
	}

	class QuoteView extends ContentPanel {

		public QuoteView() {
			setLayout(new FlowLayout(10));
			addEditionPanel(this);
			setHeading("Quote Panel");
			getStatusPanel(this);
			getClientPanel(this);
			getSupervisorPanel(this);
			getJobsPanel(this);
			getDescriptionPanel(this);
		}

	}

	protected abstract void getDescriptionPanel(QuoteView quoteView);

	protected void getStatusPanel(QuoteView quoteView) {
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
		quoteView.add(statusPanel);
	}

	protected abstract void getJobsPanel(QuoteView quoteView);

	protected abstract void getSupervisorPanel(QuoteView quoteView);

	protected abstract void getClientPanel(QuoteView quoteView);

	protected abstract void addEditionPanel(QuoteView quoteView);
}
