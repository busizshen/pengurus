package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.status.JobStatusPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public abstract class JobPanel extends MainPanel {

	DateField deadline;
	DescriptionPanel description;
	JobStatusPanel status;
	protected TranslationPanel translation;
	NumberField amount;
	NumberField price;
	ComboBox<CurrencyModel> combo;
	protected JobDTO jobDTO;

	public JobPanel(JobDTO jobDTO) {
		super();
		this.jobDTO = jobDTO;
		addInfoPanel();
		addTranslationPanel();
	}

	private void addTranslationPanel() {
		if (jobDTO.getTranslation() != null)
			translation = new TranslationPanel(new TranslationModel(
					jobDTO.getTranslation()));
		else
			translation = new TranslationPanel();
		add(translation);
		if (AuthorizationManager.canEditJob()) {
			AsyncCallback<Set<TranslationDTO>> callback = new AsyncCallback<Set<TranslationDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");

				}

				public void onSuccess(Set<TranslationDTO> result) {
					ListStore<TranslationModel> listTranslationModel = new ListStore<TranslationModel>();
					for (TranslationDTO c : result)
						listTranslationModel.add(new TranslationModel(c));
					translation.addEditPanel(listTranslationModel);
					translation.layout();
				}
			};
			AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getTranslations(callback);

		}
	}

	private void addInfoPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		hp.setBorders(true);
		hp.setAutoWidth(true);

		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		if (AuthorizationManager.canEditJobProject())
			vp.add(buttonPanel());

		vp.add(getStatusPanel());
		vp.add(addInfoForm());

		hp.add(vp);
		description = new DescriptionPanel(jobDTO.getDescription());
		description.setWidth(300);
		hp.add(description);

		add(hp);
	}

	protected abstract JobStatusPanel getStatusPanel();

	protected abstract void cancelButton();

	private HorizontalPanel buttonPanel() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);
		if (AuthorizationManager.canEditJob()) {
			Button b = new Button("Update",
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							jobDTO.setDescription(description.getDescription());
							setTranslation();
							// jobDTO.setStatus(status.getS)
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
									mb.setMessage("ServerError");
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

			Button b2 = new Button("Delete",
					new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {

							AsyncCallback<Void> callback = new AsyncCallback<Void>() {

								public void onFailure(Throwable t) {
									MessageBox mb = new MessageBox();
									mb.setMessage(t.getMessage());
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

		Button b3 = new Button("Cancel", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				cancelButton();
			}
		});
		hp.add(b3);
		return hp;
	}

	protected abstract void setTranslation();

	private FormPanel addInfoForm() {

		FormPanel infoForm = new FormPanel();
		infoForm.setHeaderVisible(false);
		infoForm.setBorders(true);

		amount = new NumberField();
		amount.setFieldLabel("Price");
		amount.setData("text", "Enter your amount and choose Currnecy");
		amount.setReadOnly(true);
		amount.setValue(jobDTO.getAmount());
		infoForm.add(amount);

		price = new NumberField();
		price.setFieldLabel("Price");
		price.setData("text", "Enter your price and choose Currnecy");
		price.setReadOnly(true);
		infoForm.add(price);

		final ListStore<CurrencyModel> listCurrencyModel = new ListStore<CurrencyModel>();
		combo = new ComboBox<CurrencyModel>();
		combo.setFieldLabel("Currency");
		combo.setDisplayField("currency");
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setStore(listCurrencyModel);
		combo.setData("text", "Choose Language");
		combo.setReadOnly(true);
		infoForm.add(combo);
		if (jobDTO.getPrice() != null) {
			price.setValue(jobDTO.getPrice().getPrice());
			combo.setValue(new CurrencyModel(jobDTO.getPrice().getCurrency()));
		}

		deadline = new DateField();
		deadline.setFieldLabel("Deadline");
		deadline.setData("text", "Enter your birthday");
		deadline.setValue(jobDTO.getDeadline());

		if (!AuthorizationManager.canEditJob()) {
			deadline.setReadOnly(false);
			combo.setReadOnly(false);
			price.setReadOnly(false);
			amount.setReadOnly(false);
			AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");
				}

				public void onSuccess(Set<CurrencyTypeDTO> result) {
					for (CurrencyTypeDTO c : result)
						listCurrencyModel.add(new CurrencyModel(c));
				}
			};
			AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getCurrency(callback);
		}
		infoForm.add(deadline);

		return infoForm;
	}

}