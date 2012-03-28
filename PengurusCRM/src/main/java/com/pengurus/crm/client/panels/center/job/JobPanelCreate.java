package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
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

	public JobPanelInfo getPanel(/* Listener<DomEvent> listenerCreateJob */) {
		// this.listenerCreateJob = listenerCreateJob;
		JobPanelInfo jobPanel = new JobPanelInfo();
		return jobPanel;
	}

	public class JobPanelInfo extends LayoutContainer {

		private VerticalPanel vp;
		private FormData formData;
		private TranslationPanel translation;

		public JobPanelInfo() {
			formData = new FormData("-20");
			vp = new VerticalPanel();
			vp.setSpacing(10);
			createForm1();
			add(vp);
		}

		private void createForm1() {
			FormPanel simple = new FormPanel();
			simple.setHeading("Job Panel");
			simple.setFrame(true);
			simple.setAutoHeight(true);
			simple.setAutoWidth(true);

			/*ComponentPlugin plugin = new ComponentPlugin() {
				public void init(Component component) {
					component.addListener(Events.Render,
							new Listener<ComponentEvent>() {
								public void handleEvent(ComponentEvent be) {
									El elem = be.getComponent().el()
											.findParent(".x-form-element", 3);
									// should style in external CSS rather than
									// directly
									elem.appendChild(XDOM
											.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>"
													+ be.getComponent()
															.getData("text")
													+ "</div>"));
								}
							});
				}
			};*/

			final DateField date = new DateField();
			date.setFieldLabel("Deadline");
			//date.addPlugin(plugin);
			date.setAllowBlank(false);
			date.setData("text", "Enter deadline");
			simple.add(date, formData);

			translation = new TranslationPanelChange(listTranslationModel);
			translation.setAllowBlank(false);
			simple.add(translation);

			final NumberField amount = new NumberField();
			amount.setFieldLabel("Price");
			amount.setAllowBlank(false);
		//	amount.addPlugin(plugin);
			amount.setData("text", "Enter your amount and choose Currnecy");
			simple.add(amount);
			
			final NumberField price = new NumberField();
			price.setFieldLabel("Price");
			price.setAllowBlank(false);
		//	amount.addPlugin(plugin);
			price.setData("text", "Enter your price and choose Currnecy");
			simple.add(price);

			final ComboBox<CurrencyModel> combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setStore(listCurrencyModel);
		//	combo.addPlugin(plugin);
			combo.setData("text", "Choose Language");
			combo.setAllowBlank(false);
			simple.add(combo, formData);

			final DescriptionPanel descr = new DescriptionPanel();
			simple.add(descr, formData);

			Button buttonSubmit = new Button("Submit",
					new SelectionListener<ButtonEvent>() {

						@Override
						public void componentSelected(ButtonEvent ce) {
							jobDTO = new JobDTO();
							jobDTO.setDeadline(date.getValue());
							jobDTO.setDescription(descr.getDescription());
							jobDTO.setAmount(amount.getValue().intValue());
							PriceDTO priceDTO = new PriceDTO();
							priceDTO.setPrice(price.getValue().intValue());
							priceDTO.setCurrency(combo.getValue()
									.getCurrencyDTO());
							jobDTO.setPrice(priceDTO);
							jobDTO.setTranslation(translation.getTranslation()
									.getTranslationDTO());
							jobDTO.setStatus(StatusDTO.open);
							AsyncCallback<JobDTO> callback = new AsyncCallback<JobDTO>() {

								public void onFailure(Throwable t) {
									Window.Location
											.assign("/spring_security_login");
								}

								public void onSuccess(JobDTO result) {
									jobDTO = result;
								}
							};
							JobServiceAsync service = (JobServiceAsync) GWT
									.create(JobService.class);
							service.createJob(jobDTO, callback);
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

			vp.add(simple);
		}
	}
}
