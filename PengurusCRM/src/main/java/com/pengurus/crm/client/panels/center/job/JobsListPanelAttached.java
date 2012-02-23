package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.shared.dto.JobDTO;

public class JobsListPanelAttached extends JobsListPanel {

	private ListStore<JobModel> jobs = new ListStore<JobModel>();
	public JobsListPanelAttached(Set<JobDTO> list){
		for(JobDTO j : list ){
			this.jobs.add(new JobModel(j));
		}
	}
	@Override
	protected ListStore<JobModel> getList() {
		return jobs;
	}
	public ModelList getPanel() {
		ModelList ml = new ModelList();
		
		return ml;
	}
	
	@Override
	protected void setStyle(ContentPanel cp) {
		cp.setCollapsible(true);
		cp.setAnimCollapse(true);
	}

}
