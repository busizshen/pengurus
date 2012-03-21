package com.pengurus.crm.client.panels.center.status;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.shared.dto.StatusDTO;

public class QuoteStatusPanel extends LayoutContainer {

	protected final Listener<DomEvent> listenerGenerateProject;
	private Listener<DomEvent> listenerChangeStatus;
	public Listener<DomEvent> listenerBackStatus;

	public QuoteStatusPanel(StatusDTO status, Listener<DomEvent> listener1,
			Listener<DomEvent> listener2,Listener<DomEvent> listener3) {
		this.listenerGenerateProject = listener2;
		this.listenerChangeStatus = listener1;
		this.listenerBackStatus = listener3;
		setLayout(new FlowLayout(10));
		VerticalPanel vp = new VerticalPanel();
		vp.setHeight("Status panel");
		QuoteStatusBar ss;
		if (status != null)
			ss = new QuoteStatusBar(status.toInt());
		else
			ss = new QuoteStatusBar(0);
		ss.setBorders(true);
		ss.setAutoHeight(true);
		ss.setHorizontalAlign(HorizontalAlignment.CENTER);
		ss.setVerticalAlign(VerticalAlignment.MIDDLE);
		vp.add(ss);
		add(vp);
	}

	private class QuoteStatusBar extends HorizontalPanel {
		Label[] list = new Label[7];
		Button generateProject;
		Button nextStatus;
		Button reOpen;
		int st = 0;

		public QuoteStatusBar(int status) {
			this.st = status;
			getLabel(0, "open", (status >= 1));
			getLabel(1, "in progress", (status >= 2));
			getLabel(2, "resolved", (status >= 3));
			getLabel(3, "verificated", (status >= 4));
			getLabel(4, "accepted", (status >= 5));
			getLabel(5, "accounted", (status >= 6));
			getLabel(6, "closed", (status == 7));
			for (Label l : list) {
				add(l);
			}
			if (AuthorizationManager.hasExecutiveAccess()) {
				generateProject = new Button("Generate Project");
				generateProject.addListener(Events.OnClick,
						listenerGenerateProject);
				add(generateProject);

			}
			if (AuthorizationManager.canChangeQuoteStatus()) {
				nextStatus = new Button("Next Status", null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								list[st].setStyleAttribute("background",
										"green");
								st++;
								setVisibility();
							}
						});

				nextStatus.addListener(Events.OnClick, listenerChangeStatus);
				add(nextStatus);
			}
			if (AuthorizationManager.canReOpenQuote()) {
				reOpen = new Button("ReOpen", null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								st--;
								list[st].setStyleAttribute("background",
										"red");
								setVisibility();
							}
						});

				reOpen.addListener(Events.OnClick, listenerBackStatus);
				add(reOpen);
			}

			setVisibility();
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
			if (((st <= 3 || st == 6) && AuthorizationManager
					.hasExecutiveAccess())
					|| (st == 4 && AuthorizationManager.hasClientAccess())
					|| (st == 5 && AuthorizationManager
							.hasAccountantAccess())) {
				nextStatus.setVisible(true);
			} else
				nextStatus.setVisible(false);
			if (AuthorizationManager.hasExecutiveAccess()) {
				if (st == 5) {
					generateProject.setVisible(true);
				} else
					generateProject.setVisible(false);
			}
			if (AuthorizationManager.hasExecutiveAccess() && st == 3)
					reOpen.setVisible(true);
			else if(AuthorizationManager.hasClientAccess() && st == 4)
					reOpen.setVisible(true);
			else reOpen.setVisible(false);
			this.layout();
		}

	}
}