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
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.TaskDTO;

public class TaskStatusPanel extends LayoutContainer {

	TaskDTO taskDTO;
	

	public TaskStatusPanel(TaskDTO taskDTO) {
		this.taskDTO = taskDTO;
		setLayout(new FlowLayout(10));
		VerticalPanel vp = new VerticalPanel();
		vp.setHeight("Status panel");

		if (taskDTO.getStatus() == null)
			taskDTO.setStatus(StatusDTO.open);

		TaskStatusBar ss;
		ss = new TaskStatusBar(taskDTO.getStatus().toInt());
		ss.setBorders(true);
		ss.setAutoHeight(true);
		ss.setHorizontalAlign(HorizontalAlignment.CENTER);
		ss.setVerticalAlign(VerticalAlignment.MIDDLE);
		vp.add(ss);
		add(vp);
	}
	
	public StatusDTO getStatus(){
		return taskDTO.getStatus();
	}

	private class TaskStatusBar extends HorizontalPanel {
		Label[] list = new Label[7];
		Button nextStatus;
		Button reOpen;
		HorizontalPanel hp1;
		HorizontalPanel hp2;
		int st = 0;

		public TaskStatusBar(int status) {
			this.st = status;
			hp1 = new HorizontalPanel();
			hp2 = new HorizontalPanel();
			getLabels();

			if (AuthorizationManager.canChangeQuoteStatus()) {
				nextStatus = new Button("Next Status", null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								list[st].setStyleAttribute("background",
										"green");
								st++;
								setVisibility();
								taskDTO.setStatus(taskDTO.getStatus()
										.increase());
								taskDTO.updateStatus();
								refresh();
							}
						});

				hp2.add(nextStatus);
			}
			if (AuthorizationManager.canReOpenQuote()) {
				reOpen = new Button("ReOpen", null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								st--;
								list[st].setStyleAttribute("background", "red");
								setVisibility();
								taskDTO.setStatus(taskDTO.getStatus()
										.decrease());
								taskDTO.updateStatus();
								refresh();
							}
						});
				hp2.add(reOpen);
			}
			add(hp1);
			add(hp2);
			setVisibility();
		}

		private void refresh() {

		}

		private void getLabels() {
			getLabel(0, "open", (st >= 1));
			getLabel(1, "in progress", (st >= 2));
			getLabel(2, "resolved", (st >= 3));
			getLabel(3, "verificated", (st >= 4));
			getLabel(4, "accepted", (st >= 5));
			getLabel(5, "accounted", (st >= 6));
			getLabel(6, "closed", (st == 7));
			for (Label l : list) {
				hp1.add(l);
			}
		}

		private void getLabel(int num, String descr, boolean status) {
			list[num] = new Label();
			Label l = list[num];
			l.setBorders(true);
			if (status)
				l.setStyleAttribute("background", "green");
			else
				l.setStyleAttribute("background", "red");
			l.setWidth(100);
			l.setSize(100, 60);
			l.setText(descr);
			l.setShadowOffset(windowResizeDelay);
		}

		private void setVisibility() {
			if ((AuthorizationManager.hasExecutiveAccess())
					|| (st == 5 && AuthorizationManager.hasAccountantAccess())
					|| (st <= 3 && AuthorizationManager
							.hasTranslatorAccess(taskDTO))
					|| (st == 4 && AuthorizationManager
							.hasVerifivatorAccess(taskDTO))
					|| AuthorizationManager.hasProjectManagerAccess(taskDTO)) {
				nextStatus.setVisible(true);
			} else
				nextStatus.setVisible(false);
			if (AuthorizationManager.hasExecutiveAccess() && st == 3)
				reOpen.setVisible(true);
			else if (AuthorizationManager.hasClientAccess() && st == 4)
				reOpen.setVisible(true);
			else
				reOpen.setVisible(false);
			this.layout();
		}

	}
}
