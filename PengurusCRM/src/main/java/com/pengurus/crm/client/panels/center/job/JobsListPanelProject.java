package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.JobModel;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;

public abstract class JobsListPanelProject extends JobsListPanel {

	protected ProjectDTO projectDTO;

	public JobsListPanelProject(ProjectDTO projectDTO, int height) {
		super(height);
		this.projectDTO = projectDTO;
		for (JobDTO j : projectDTO.getJobs()) {
			this.jobs.add(new JobModel(j));
		}
	}

	protected GridCellRenderer<JobModel> getButtonRenderer() {

		GridCellRenderer<JobModel> buttonRenderer = new GridCellRenderer<JobModel>() {

			private boolean init;

			public Object render(final JobModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<JobModel> store, Grid<JobModel> grid) {
				if (!init) {
					init = true;
	}
				Button b = new Button("PREVIEW",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

									public void onFailure(Throwable t) {
									}

									public void onSuccess(JobDTO result) {
										JobPanelProject jobPanel = new JobPanelProject(
												result, projectDTO);
										jobPanel.setAsMain();
									}
								};
								JobServiceAsync service = (JobServiceAsync) GWT
										.create(JobService.class);
								service.getJob(model.getJobDTO().getId(),
										callback);

							}
						});
				b.setToolTip("Click to see");

				return b;
			}
		};
		return buttonRenderer;
	}

}
