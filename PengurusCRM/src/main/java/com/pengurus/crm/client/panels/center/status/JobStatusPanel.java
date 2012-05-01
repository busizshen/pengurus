package com.pengurus.crm.client.panels.center.status;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.ProjectDTO;
import com.pengurus.crm.shared.dto.StatusDTO;

public class JobStatusPanel extends LayoutContainer {

	private static final String COLOUR_OK = "#BBD5F7";
	private static final String COLOUR_NOT = "#ECECEC";
	private static final String NEXT_STATUS = "next status";
	private static final String REOPEN = "reopen";

	JobDTO jobDTO;
	ProjectDTO projectDTO;

	public JobStatusPanel(JobDTO jobDTO, ProjectDTO projectDTO) {

		this.jobDTO = jobDTO;
		this.projectDTO = projectDTO;

		setLayout(new FlowLayout(10));

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHeight("Status panel");

		TaskStatusBar taskStatusBar = new TaskStatusBar(
				jobDTO.getStatus() == null ? StatusDTO.open.toInt() : jobDTO
						.getStatus().toInt());
		taskStatusBar.setBorders(true);
		taskStatusBar.setAutoHeight(true);
		taskStatusBar.setHorizontalAlign(HorizontalAlignment.CENTER);
		taskStatusBar.setVerticalAlign(VerticalAlignment.MIDDLE);

		verticalPanel.add(taskStatusBar);
		add(verticalPanel);
	}

	public StatusDTO getStatus() {
		return jobDTO.getStatus();
	}

	private class TaskStatusBar extends HorizontalPanel {
		Label[] labelsList = new Label[7];
		Button nextStatus;
		Button reOpen;
		HorizontalPanel horizontalPanelA;
		HorizontalPanel horizontalPanelB;
		int status = 0;

		public TaskStatusBar(int statusNo) {
			this.status = statusNo;
			horizontalPanelA = new HorizontalPanel();
			horizontalPanelB = new HorizontalPanel();

			for (int i = 0; i < StatusDTO.values().length; i++) {
				labelsList[i] = prepareLabel(i);
				add(labelsList[i]);
			}

			if (AuthorizationManager.canChangeQuoteStatus()) {
				nextStatus = new Button(NEXT_STATUS, null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								labelsList[++status].setStyleAttribute(
										"background", COLOUR_OK);
								setVisibility();
								jobDTO.setStatus(jobDTO.getStatus().increase());
								jobDTO.updateStatus();
							}
						});

				horizontalPanelB.add(nextStatus);
			}
			if (AuthorizationManager.canReOpenQuote()) {
				reOpen = new Button(REOPEN, null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								labelsList[status--].setStyleAttribute(
										"background", COLOUR_NOT);
								setVisibility();
								jobDTO.setStatus(jobDTO.getStatus().decrease());
								jobDTO.updateStatus();
							}
						});
				horizontalPanelB.add(reOpen);
			}
			add(horizontalPanelA);
			add(horizontalPanelB);
			setVisibility();
		}

		private Label prepareLabel(int statusNo) {
			Label label = new Label();
			label.setBorders(true);
			label.setStyleAttribute("background",
					status >= statusNo ? COLOUR_OK : COLOUR_NOT);
			label.setWidth(150);
			label.setHeight(100);
			label.setText(StatusDTO.fromInt(statusNo));
			label.setShadowOffset(windowResizeDelay);
			return label;
		}

		private void setVisibility() {
			nextStatus.setVisible(status == 6 ? false : AuthorizationManager
					.canEditProject(projectDTO) ? true : false);

			reOpen.setVisible((AuthorizationManager.hasExecutiveAccess() && status == 2) ? true
					: false);

			this.layout();
		}

	}

}
