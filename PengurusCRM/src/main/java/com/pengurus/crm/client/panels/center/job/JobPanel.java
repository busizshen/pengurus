package com.pengurus.crm.client.panels.center.job;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.status.JobStatusPanel;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;

public abstract class JobPanel extends MainPanel {

	DateField deadline;
	DescriptionPanel description;
	JobStatusPanel jobStatusPanel;
	protected TranslationPanel translation;
	NumberField amount;
	NumberField price;
	ComboBox<CurrencyModel> combo;
	protected JobDTO jobDTO;

	public JobPanel(JobDTO jobDTO, int height) {
		super(height);
		setHeading(myConstants.Job());
		this.jobDTO = jobDTO;
	}

	protected abstract void addTranslationPanel(final VerticalPanel vp);


	protected abstract void addInfoPanel();
	
	protected abstract void addStatusPanel(VerticalPanel vp);
	
	protected abstract void setTranslation();

	protected abstract DescriptionPanel addDescriptionPanel();

	
	protected abstract void cancelButton();

	protected HorizontalPanel getbuttonPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		//hp.setSpacing(5);
		if (AuthorizationManager.canEditJob()) {
			Button b = new Button(myConstants.Update(),
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							jobDTO.setDescription(description.getDescription());
							jobDTO.setTranslation(translation.getTranslation().getTranslationDTO());
							jobDTO.setDeadline(deadline.getValue());
							if (price.getValue() != null
									&& combo.getValue() != null)
								jobDTO.setPrice(new PriceDTO(price.getValue()
										.intValue(), combo.getValue()
										.getCurrencyDTO()));
							if (amount.getValue() != null)
								jobDTO.setAmount(amount.getValue().intValue());

							AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

								public void onFailure(Throwable t) {
									MessageBox mb = new MessageBox();
									mb.setMessage(myMessages.ServerError(t.getMessage()));
									mb.show();
								}

								@Override
								public void onSuccess(JobDTO result) {
									cancelButton();
								}

							};
							JobServiceAsync service = (JobServiceAsync) GWT
									.create(JobService.class);
							service.updateJob(jobDTO, callback);

						}
					});
			hp.add(b);

			Button b2 = new Button(myConstants.Delete(),
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {

							AsyncCallback<Void> callback = new AsyncCallback<Void>() {

								public void onFailure(Throwable t) {
									MessageBox mb = new MessageBox();
									mb.setMessage(myMessages.ServerError(t.getMessage()));
									mb.show();
								}

								@Override
								public void onSuccess(Void result) {
									cancelButton();
								}

							};
							JobServiceAsync service = (JobServiceAsync) GWT
									.create(JobService.class);
							service.deleteJob(jobDTO, callback);
						}
					});
			hp.add(b2);
		}

		Button b3 = new Button(myConstants.Cancel(), new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				cancelButton();
			}
		});
		hp.add(b3);
		return hp;
	}
	
	protected FieldSet getDeadlinePanel() {

		FieldSet deadlinePanel = new FieldSet();
		deadlinePanel.setHeading(myConstants.Deadline());
		deadline = new DateField();
		deadline.setData("text", myConstants.EnterDeadline());
		if (jobDTO != null)
			deadline.setValue(jobDTO.getDeadline());
		deadlinePanel.add(deadline);
		if (!AuthorizationManager.canEditJob()) {
			deadline.setReadOnly(true);
		}
		deadlinePanel.setStyleAttribute("margin-right", "40px");
		return deadlinePanel;
	}

	protected abstract void addFilesPanel(HorizontalPanel hp0);
}