package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelQuoteEdit extends JobsListPanelQuote {

	public JobsListPanelQuoteEdit(QuoteDTO quoteDTO){
		super(quoteDTO, 300);
		setHeading("Jobs");
		modelList = new ModelList(200, 825);
		Button createButton = new Button("Create New Job", new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				showCreateJobPanel();
			}});
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.CENTER);
		vp.add(createButton);
		vp.add(modelList);
		add(vp);
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

	public void refreshList(JobDTO result) {
		modelList.getGrid().stopEditing();
		modelList.getStore().add(new JobModel(result));
		modelList.getGrid().startEditing(0, 0);
	}

}
