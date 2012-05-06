package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.AuthorizationManager;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelChange;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationPanelView;
import com.pengurus.crm.client.panels.center.description.DescriptionPanel;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelEdit;
import com.pengurus.crm.client.panels.center.description.DescriptionPanelView;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanel;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelInput;
import com.pengurus.crm.client.panels.center.filespanel.FilesPanelOutput;
import com.pengurus.crm.client.panels.center.quote.QuotePanel;
import com.pengurus.crm.client.panels.center.quote.QuotePanelView;
import com.pengurus.crm.client.panels.center.status.JobStatusPanelQuote;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.QuoteService;
import com.pengurus.crm.client.service.QuoteServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.QuoteDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class JobPanelQuote extends JobPanel {

	private QuoteDTO quoteDTO;

	public JobPanelQuote(JobDTO jobDTO, QuoteDTO quoteDTO) {
		super(jobDTO, 950);
		setLayout(new BorderLayout());
		this.quoteDTO = quoteDTO;
		addInfoPanel();
	}

	@Override
	protected void setTranslation() {
		if (translation.getTranslation().getTranslationDTO() != jobDTO
				.getTranslation()) {
			jobDTO.setTranslation(translation.getTranslation()
					.getTranslationDTO());
		}
	}

	@Override
	protected void cancelButton() {
		AsyncCallback<QuoteDTO> callback = new AsyncCallback<QuoteDTO>() {

			public void onFailure(Throwable t) {
				MessageBox mb = new MessageBox();
				mb.setMessage("Server Error Cancel");
				mb.show();
			}

			@Override
			public void onSuccess(QuoteDTO result) {
				QuotePanel quotePanel = new QuotePanelView(result);
				quotePanel.setAsMain();
			}
		};
		QuoteServiceAsync service = (QuoteServiceAsync) GWT
				.create(QuoteService.class);
		service.getQuote(quoteDTO.getId(), callback);

	}

	@Override
	protected void addStatusPanel(VerticalPanel vp) {
		this.jobStatusPanel = new JobStatusPanelQuote(jobDTO, quoteDTO);
		vp.add(jobStatusPanel);
	}

	@Override
	protected void addTranslationPanel(final VerticalPanel vp) {
		if (AuthorizationManager.canEditJob()) {
			AsyncCallback<Set<TranslationDTO>> callback = new AsyncCallback<Set<TranslationDTO>>() {

				public void onFailure(Throwable t) {
					Window.Location.assign("/spring_security_login");

				}

				public void onSuccess(Set<TranslationDTO> result) {
					
					ListStore<TranslationModel> listTranslationModel = new ListStore<TranslationModel>();
					for (TranslationDTO c : result)
						listTranslationModel.add(new TranslationModel(c));
					if (jobDTO.getTranslation() != null)
						translation = new TranslationPanelChange(
								jobDTO.getTranslation(), listTranslationModel,
								jobDTO.getAmount(), jobDTO.getPrice());
					else
						translation = new TranslationPanelChange(
								listTranslationModel, jobDTO.getAmount(),
								jobDTO.getPrice());
					vp.add(translation);
					HorizontalPanel filesPanel = new HorizontalPanel();
					addFilesPanel(filesPanel);
					vp.add(filesPanel);

				}
			};
			AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
					.create(AdministrationService.class);
			service.getTranslations(callback);

		} else {
			translation = new TranslationPanelView(jobDTO.getTranslation(),
					jobDTO.getAmount(), jobDTO.getPrice());
			vp.add(translation);
			HorizontalPanel filesPanel = new HorizontalPanel();
			addFilesPanel(filesPanel);
			vp.add(filesPanel);
		}
	}

	@Override
	protected void addInfoPanel() {

		
		VerticalPanel mainVerticalPanel = new VerticalPanel();
		HorizontalPanel topHorizontalPanel = new HorizontalPanel();
		mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainVerticalPanel.setSpacing(15);
		
		topHorizontalPanel.add(getDeadlinePanel());
		topHorizontalPanel.add(addDescriptionPanel());
		topHorizontalPanel.add(getbuttonPanel());
		
		mainVerticalPanel.add(topHorizontalPanel);
		
		//mainVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		addStatusPanel(mainVerticalPanel);
		
		addInfoForm(mainVerticalPanel);
		addTranslationPanel(mainVerticalPanel);
		
		add(mainVerticalPanel);

	}

	private void addInfoForm(VerticalPanel vp) {

		if (AuthorizationManager.canEditJob()) {

			FormPanel infoForm = new FormPanel();
			infoForm.setHeaderVisible(false);
			infoForm.setBorders(true);

			amount = new NumberField();
			amount.setFieldLabel("Amount");
			amount.setData("text", "Enter amount");
			amount.addListener(Events.OnChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});
			infoForm.add(amount);

			price = new NumberField();
			price.setFieldLabel("Price");
			price.setData("text", "Enter price and choose Currnecy");
			price.addListener(Events.OnChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});
			infoForm.add(price);

			final ListStore<CurrencyModel> listCurrencyModel = new ListStore<CurrencyModel>();
			combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setStore(listCurrencyModel);
			combo.setData("text", "Choose Language");
			combo.addListener(Events.OnChange, new Listener<BaseEvent>() {

				@Override
				public void handleEvent(BaseEvent be) {
					updateTranslation();

				}
			});
			infoForm.add(combo);
			if (jobDTO.getAmount() != null)
				amount.setValue(jobDTO.getAmount());
			if (jobDTO.getPrice() != null) {
				price.setValue(jobDTO.getPrice().getPrice());
				combo.setValue(new CurrencyModel(jobDTO.getPrice()
						.getCurrency()));
			}

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

			vp.add(infoForm);
		}

	}

	protected DescriptionPanel addDescriptionPanel() {
		if (AuthorizationManager.canEditJob()) {
			description = new DescriptionPanelEdit(jobDTO.getDescription(), 50, 400);
			description.setStyleAttribute("margin-right", "40px");
			return description;
		} else {
			description = new DescriptionPanelView(jobDTO.getDescription(), 50, 400);
			description.setStyleAttribute("margin-right", "40px");
			return description;
		}
	}

	protected void updateTranslation() {
		Integer amountVal = 0;
		if (amount.getValue() != null)
			amountVal = amount.getValue().intValue();
		PriceDTO priceVal = null;
		if (combo.getValue() != null && price.getValue() != null)
			priceVal = new PriceDTO(price.getValue().intValue(), combo
					.getValue().getCurrencyDTO());
		translation.setTranslationValues(translation.getTranslation().getTranslationDTO(),
				amountVal, priceVal);

	}

	@Override
	protected void addFilesPanel(HorizontalPanel hp0) {
		FilesPanel filesPanelIn = new FilesPanelInput(quoteDTO.getId(),
				jobDTO.getId(), new Long(0), true, true);
		hp0.add(filesPanelIn);
		filesPanelIn.setStyleAttribute("margin-right", "40px");
		FilesPanel filesPanelOut = new FilesPanelOutput(quoteDTO.getId(),
				jobDTO.getId(), new Long(0), true, true);
		hp0.add(filesPanelOut);
	}
}