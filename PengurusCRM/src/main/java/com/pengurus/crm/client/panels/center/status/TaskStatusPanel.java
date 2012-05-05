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
import com.pengurus.crm.shared.dto.StatusTaskDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskStatusPanel extends LayoutContainer {

	private static final String COLOUR_OK = "#BBD5F7";
	private static final String COLOUR_NOT = "#ECECEC";
	private static final String NEXT_STATUS = "next status";
	private static final String REOPEN = "reopen";

	TaskDTO taskDTO;

	public TaskStatusPanel(TaskDTO taskDTO) {

		this.taskDTO = taskDTO;

		setLayout(new FlowLayout(10));

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHeight("Status panel");

		TaskStatusBar taskStatusBar = new TaskStatusBar(
				taskDTO.getStatus() == null ? StatusTaskDTO.open.toInt()
						: taskDTO.getStatus().toInt());
		taskStatusBar.setBorders(true);
		taskStatusBar.setAutoHeight(true);
		taskStatusBar.setHorizontalAlign(HorizontalAlignment.CENTER);
		taskStatusBar.setVerticalAlign(VerticalAlignment.MIDDLE);

		verticalPanel.add(taskStatusBar);
		add(verticalPanel);
	}

	public StatusTaskDTO getStatus() {
		return taskDTO.getStatus();
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

			for (int i = 0; i < StatusTaskDTO.values().length; i++) {
				labelsList[i] = prepareLabel(i);
				add(labelsList[i]);
			}

			nextStatus = new Button(NEXT_STATUS, null,
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							labelsList[++status].setStyleAttribute(
									"background", COLOUR_OK);
							setVisibility();
							taskDTO.setStatus(taskDTO.getStatus().increase());
							taskDTO.updateStatus();
						}
					});

			horizontalPanelB.add(nextStatus);

			reOpen = new Button(REOPEN, null,
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							labelsList[status--].setStyleAttribute(
									"background", COLOUR_NOT);
							setVisibility();
							taskDTO.setStatus(taskDTO.getStatus().decrease());
							taskDTO.updateStatus();
						}
					});
			horizontalPanelB.add(reOpen);
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
			label.setText(StatusTaskDTO.fromInt(statusNo));
			label.setShadowOffset(windowResizeDelay);
			return label;
		}

		private void setVisibility() {
			nextStatus
					.setVisible((status == 6 ? false
							: ((AuthorizationManager.hasExecutiveAccess()) || AuthorizationManager
									.hasProjectManagerAccess(taskDTO)))
									|| (status == 4 && AuthorizationManager
											.hasAccountantAccess())
									|| (status <= 2 && AuthorizationManager
											.hasTranslatorAccess(taskDTO))
									|| (status == 3 && AuthorizationManager
											.hasVerificatorAccess(taskDTO)) ? true
									: false);

			reOpen.setVisible((AuthorizationManager.hasVerificatorAccess() && status == 2)
					|| (AuthorizationManager.hasProjectManagerAccess(taskDTO) && status == 3 || status == 2) ? true
					: false);

			this.layout();
		}

	}
}
