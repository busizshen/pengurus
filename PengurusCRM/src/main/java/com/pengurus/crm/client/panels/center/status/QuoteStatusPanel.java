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
import com.pengurus.crm.shared.dto.StatusQuoteDTO;

public class QuoteStatusPanel extends LayoutContainer {

	private static final String COLOUR_OK = "#BBD5F7";
	private static final String COLOUR_NOT = "#ECECEC";
	private static final String NEXT_STATUS = "next status";
	private static final String REOPEN = "reopen";

	protected final Listener<DomEvent> listenerGenerateProject;
	private Listener<DomEvent> listenerChangeStatus;
	public Listener<DomEvent> listenerBackStatus;

	public QuoteStatusPanel(StatusQuoteDTO status,
			Listener<DomEvent> listenerChangeStatus,
			Listener<DomEvent> listenerGenerateProject,
			Listener<DomEvent> listenerBackStatus) {

		this.listenerChangeStatus = listenerChangeStatus;
		this.listenerGenerateProject = listenerGenerateProject;
		this.listenerBackStatus = listenerBackStatus;

		setLayout(new FlowLayout(10));

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHeight("Status panel");

		QuoteStatusBar quoteStatusBar;

		quoteStatusBar = (status != null) ? new QuoteStatusBar(status.toInt())
				: new QuoteStatusBar(0);
		quoteStatusBar.setBorders(true);
		quoteStatusBar.setAutoHeight(true);
		quoteStatusBar.setHorizontalAlign(HorizontalAlignment.CENTER);
		quoteStatusBar.setVerticalAlign(VerticalAlignment.MIDDLE);
		quoteStatusBar.setSpacing(2);

		verticalPanel.add(quoteStatusBar);
		add(verticalPanel);
	}

	private class QuoteStatusBar extends HorizontalPanel {
		Label[] labelsList = new Label[8];
		Button generateProject;
		Button nextStatus;
		Button reOpen;
		int status = 0;

		public QuoteStatusBar(int statusNo) {
			this.status = statusNo;

			for (int i = 0; i < StatusQuoteDTO.values().length; i++) {
				labelsList[i] = prepareLabel(i);
				add(labelsList[i]);
			}

				generateProject = new Button("Generate Project");
				generateProject.addListener(Events.OnClick,
						listenerGenerateProject);
				generateProject.addListener(Events.OnClick,
						listenerChangeStatus);
				add(generateProject);
		

	
				nextStatus = new Button(NEXT_STATUS, null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								labelsList[++status].setStyleAttribute(
										"background", COLOUR_OK);
								setVisibility();
							}
						});

				nextStatus.addListener(Events.OnClick, listenerChangeStatus);
				add(nextStatus);


				reOpen = new Button(REOPEN, null,
						new SelectionListener<ButtonEvent>() {

							@Override
							public void componentSelected(ButtonEvent ce) {
								labelsList[status--].setStyleAttribute(
										"background", COLOUR_NOT);
								setVisibility();
							}
						});

				reOpen.addListener(Events.OnClick, listenerBackStatus);
				add(reOpen);
		

			setVisibility();
		}

		private Label prepareLabel(int statusNo) {
			Label label = new Label();
			label.setBorders(true);
			label.setStyleAttribute("background",
					(status >= statusNo) ? COLOUR_OK : COLOUR_NOT);
			label.setWidth(150);
			label.setHeight(100);
			label.setText(StatusQuoteDTO.fromInt(statusNo));
			label.setShadowOffset(windowResizeDelay);
			return label;
		}

		private void setVisibility() {
			nextStatus
					.setVisible((AuthorizationManager.hasClientAccess() && status == 2) ? true
							: false || status == 7 ? false
							: (((status <= 2 || status >= 6) && AuthorizationManager
									.hasExecutiveAccess()) || (status == 5 && AuthorizationManager
									.hasAccountantAccess())) ? true : false);

			generateProject.setVisible(AuthorizationManager
					.hasExecutiveAccess() && status == 3 ? true : false);

			reOpen.setVisible((AuthorizationManager.hasClientAccess() && status == 2) ? true
					: false);

			this.layout();
		}

	}
}