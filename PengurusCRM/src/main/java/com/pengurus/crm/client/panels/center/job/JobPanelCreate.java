package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelChange;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.JobService;
import com.pengurus.crm.client.service.JobServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class JobPanelCreate {
	private JobDTO jobDTO;
	protected Listener<DomEvent> listenerCreateJob;
	private Listener<DomEvent> listenerClose;
	private ListStore<CurrencyModel> listCurrencyModel;
	private ListStore<TranslationModel> listTranslationModel;

	public JobPanelCreate() {
		listCurrencyModel = new ListStore<CurrencyModel>();
		listTranslationModel = new ListStore<TranslationModel>();

		AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

			public void onFailure(Throwable t) {
			}

			public void onSuccess(Set<CurrencyTypeDTO> result) {
				for (CurrencyTypeDTO c : result)
					listCurrencyModel.add(new CurrencyModel(c));
			}
		};
		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getCurrency(callback);
		AsyncCallback<Set<TranslationDTO>> callback2 = new AsyncCallback<Set<TranslationDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<TranslationDTO> result) {
				for (TranslationDTO c : result)
					listTranslationModel.add(new TranslationModel(c));
			}
		};
		service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getTranslations(callback2);
	}

	public JobDTO getJobDTO() {
		return jobDTO;
	}

	public JobPanelInfo getPanel(Listener<DomEvent> listenerClose,
			Listener<DomEvent> listenerCreateJob) {
		this.listenerCreateJob = listenerCreateJob;
		this.listenerClose = listenerClose;
		JobPanelInfo jobPanel = new JobPanelInfo();
		return jobPanel;
	}

	public JobPanelInfo getPanel() {
		JobPanelInfo jobPanel = new JobPanelInfo();
		return jobPanel;
	}

	public class JobPanelInfo extends LayoutContainer {

		private VerticalPanel panel;
		private TranslationPanel translation;

		public JobPanelInfo() {
			panel = new VerticalPanel();
			panel.setSpacing(10);
			createForm1();
			add(panel);
		}

		private void createForm1() {
			FormPanel simple = new FormPanel();
			simple.setHeading("Job Panel");
			simple.setFrame(true);
			simple.setAutoHeight(true);
			simple.setAutoWidth(true);

			HorizontalPanel hp = new HorizontalPanel();
			hp.setSpacing(20);
			FormPanel simple2 = new FormPanel();
			simple2.setHeaderVisible(false);
			simple2.setFrame(true);
			simple2.setAutoHeight(true);
			simple2.setAutoWidth(true);

			final DateField date = new DateField();
			date.setFieldLabel("Deadline");
			date.setAllowBlank(false);
			date.setData("text", "Enter deadline");
			simple2.add(date);

			final NumberField amount = new NumberField();
			amount.setFieldLabel("Amount");
			amount.setAllowBlank(false);
			amount.setData("text", "Enter amount and choose Currnecy");
			simple2.add(amount);

			final NumberField price = new NumberField();
			price.setFieldLabel("Price");
			price.setAllowBlank(false);
			price.setData("text", "Enter price and choose Currnecy");
			simple2.add(price);

			final ComboBox<CurrencyModel> combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setStore(listCurrencyModel);
			combo.setData("text", "Choose Language");
			combo.setAllowBlank(false);
			simple2.add(combo);
			hp.add(simple2);
			final DescriptionPanel descr = new DescriptionPanel();
			hp.add(descr);

			simple.add(hp);

			translation = new TranslationPanelChange(listTranslationModel);
			translation.setAllowBlank(false);
			simple.add(translation);

			Button buttonSubmit = new Button("Submit",
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							jobDTO = new JobDTO();
							jobDTO.setDeadline(date.getValue());
							jobDTO.setDescription(descr.getDescription());
							if (amount.getValue() != null)
								jobDTO.setAmount(amount.getValue().intValue());
							if (price.getValue() != null
									&& combo.getValue() != null) {
								PriceDTO priceDTO = new PriceDTO();
								priceDTO.setPrice(price.getValue().intValue());
								priceDTO.setCurrency(combo.getValue()
										.getCurrencyDTO());
								jobDTO.setPrice(priceDTO);
							}
							if (translation.getTranslation() != null)
								jobDTO.setTranslation(translation
										.getTranslation().getTranslationDTO());
							jobDTO.setStatus(StatusDTO.open);
							if (jobDTO.checked()) {
								AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

									public void onFailure(Throwable t) {
										MessageBox mb = new MessageBox();
										mb.setMessage("Server Error");
										mb.show();
									}

									public void onSuccess(JobDTO result) {
										jobDTO = result;
									}
								};
								JobServiceAsync service = (JobServiceAsync) GWT
										.create(JobService.class);
								service.createJob(jobDTO, callback);
							} else {
								jobDTO = null;
								MessageBox mb = new MessageBox();
								mb.setMessage("Please fill all forms");
								mb.show();
							}
						}

					});
			buttonSubmit.addListener(Events.OnClick, listenerCreateJob);
			simple.addButton(buttonSubmit);
			Button buttonCancel = new Button("Cancel");
			buttonCancel.addListener(Events.OnClick, listenerClose);
			simple.addButton(buttonCancel);

			simple.setButtonAlign(HorizontalAlignment.CENTER);

			FormButtonBinding binding = new FormButtonBinding(simple);
			binding.addButton(buttonSubmit);
			
			FormButtonBinding binding2 = new FormButtonBinding(simple2);
			binding2.addButton(buttonSubmit);

			panel.add(simple);
		}
	}
}
