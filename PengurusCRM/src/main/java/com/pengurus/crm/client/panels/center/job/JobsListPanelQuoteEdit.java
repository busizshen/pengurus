package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteEdit extends JobsListPanelQuote {

	public JobsListPanelQuoteEdit(QuoteDTO quoteDTO){
		super(quoteDTO);
	}
	private void showCreateJobPanel(){
		final Window window = new Window();
		final JobPanelCreate jobPanel = new JobPanelCreate();
		Listener<DomEvent> listenerClose = new Listener<DomEvent>() { 
			@Override
			public void handleEvent(DomEvent be) {
				window.hide();
			}
		};
		window.setActive(true);
		window.add(jobPanel.getPanel(listenerClose,quoteDTO,this));
		window.setAutoWidth(true);
		window.setAutoHide(true);
		window.setEnabled(true);
		window.setClosable(false);
		window.setHeaderVisible(true);
		window.setAutoHide(false);
		window.setHeading("Create Job");
		window.show();
	}
	
	public JobsListPanelQuoteEdit getPanel() {
		setHeading("Jobs");
		modelList = new ModelList();
		Button createButton = new Button("Create New Job", new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				showCreateJobPanel();
			}});
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(createButton);
		hp.add(modelList);
		add(hp);
		return this;
	}

	public void refreshList(JobDTO result) {
		modelList.getGrid().stopEditing();
		modelList.getStore().add(new JobModel(result));
		modelList.getGrid().startEditing(0, 0);
	}

}
