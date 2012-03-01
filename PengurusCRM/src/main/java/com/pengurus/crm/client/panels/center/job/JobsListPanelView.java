package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;

public class JobsListPanelView extends JobsListPanel {

	ModelList ml;

	public JobsListPanelView(QuoteDTO quoteDTO) {
		super(quoteDTO);
	}

	public JobsListPanelView(ProjectDTO projectDTO) {
		super(projectDTO);
	}

	public ContentPanel getPanel() {
		ml = new ModelList();
		add(ml);
		setHeading("Jobs");
		setCollapsible(true);
		setAnimCollapse(true);
		collapse();
		return this;
	}


}
