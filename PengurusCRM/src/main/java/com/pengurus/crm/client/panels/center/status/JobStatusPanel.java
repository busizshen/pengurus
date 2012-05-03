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
import com.pengurus.crm.shared.dto.StatusJobDTO;

public abstract class JobStatusPanel extends LayoutContainer {

	private static final String COLOUR_OK = "#BBD5F7";
	private static final String COLOUR_NOT = "#ECECEC";
	private static final String NEXT_STATUS = "next status";
	private static final String REOPEN = "reopen";

	JobDTO jobDTO;
	protected Button nextStatus;
	protected Button reOpen;
	protected int status = 0;


	public JobStatusPanel(JobDTO jobDTO) {

		this.jobDTO = jobDTO;

		setLayout(new FlowLayout(10));

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHeight("Status panel");

		JobStatusBar jobStatusBar = new JobStatusBar(
				jobDTO.getStatus() == null ? StatusJobDTO.open.toInt() : jobDTO
						.getStatus().toInt());
		jobStatusBar.setBorders(true);
		jobStatusBar.setAutoHeight(true);
		jobStatusBar.setHorizontalAlign(HorizontalAlignment.CENTER);
		jobStatusBar.setVerticalAlign(VerticalAlignment.MIDDLE);

		verticalPanel.add(jobStatusBar);
		add(verticalPanel);
	}

	public StatusJobDTO getStatus() {
		return jobDTO.getStatus();
	}


	protected abstract void setVisibility();

	protected class JobStatusBar extends HorizontalPanel {
		Label[] labelsList = new Label[7];
		HorizontalPanel horizontalPanelA;
		HorizontalPanel horizontalPanelB;

		public JobStatusBar(int statusNo) {
			status = statusNo;
			horizontalPanelA = new HorizontalPanel();
			horizontalPanelB = new HorizontalPanel();

			for (int i = 0; i < StatusJobDTO.values().length; i++) {
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
			label.setText(StatusJobDTO.fromInt(statusNo));
			label.setShadowOffset(windowResizeDelay);
			return label;
		}
	}

}
