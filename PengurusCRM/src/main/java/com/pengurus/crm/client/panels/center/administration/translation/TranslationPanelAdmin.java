package com.pengurus.crm.client.panels.center.administration.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.LanguageModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.models.TranslationTypeModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.exceptions.DependencyException;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.LanguageDTO;
import com.pengurus.crm.shared.dto.PriceDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;

public class TranslationPanelAdmin extends MainPanel {

	private VerticalPanel verticalPanel;
	private FormPanel createForm;
	private ComboBox<LanguageModel> fromCombo;
	private ComboBox<LanguageModel> toCombo;
	private ComboBox<TranslationTypeModel> translationTypeCombo;
	private ComboBox<CurrencyModel> currencyCombo;
	private NumberField defaultPriceField;
	private FormData formData;
	private Button createButton;
	private Button removeButton;
	private Grid<TranslationModel> grid;

	public TranslationPanelAdmin() {
		super(600);
		setHeading(myConstants.Translations());
		createVerticalPanel();
		createNewTranslationForm();
		createButton();
		createTranslationGrid();
		add(verticalPanel);
	}

	private void createTranslationGrid() {
		final ListStore<TranslationModel> list = new ListStore<TranslationModel>();
		AsyncCallback<Set<TranslationDTO>> callback = new AsyncCallback<Set<TranslationDTO>>() {

			@Override
			public void onSuccess(Set<TranslationDTO> result) {
				for (TranslationDTO translation : result)
					list.add(new TranslationModel(translation));
			}

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.info(myConstants.Failure(),
						myMessages.UploadFailed(myConstants.translations()),
						null);
			}
		};

		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getTranslations(callback);

		list.sort("from", SortDir.ASC);

		RowNumberer r = new RowNumberer();
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		configs.add(r);

		ColumnConfig column = new ColumnConfig();

		column.setId("from");
		column.setHeader(myConstants.From());
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("to");
		column.setHeader(myConstants.To());
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("type");
		column.setHeader(myConstants.TranslationType());
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("defaultPrice");
		column.setHeader(myConstants.Price());
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("defaultPriceCurrency");
		column.setHeader(myConstants.Currency());
		column.setWidth(200);
		configs.add(column);

		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<TranslationModel>(list, cm);
		grid.addPlugin(r);
		grid.getView().setForceFit(true);

		removeButton = new Button(myMessages.RemoveSelectedFem(myConstants
				.translation()), new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItem() != null) {

					AsyncCallback<TranslationDTO> callback = new AsyncCallback<TranslationDTO>() {

						@Override
						public void onSuccess(TranslationDTO result) {
							grid.getStore().remove(
									grid.getSelectionModel().getSelectedItem());

							MessageBox.info(myConstants.Success(),
									myConstants.DeleteSuccess(), null);
						}

						@Override
						public void onFailure(Throwable caught) {
							if (caught instanceof DependencyException)
								MessageBox.info(
										myConstants.Failure(),
										myMessages
												.DependencyExceptionFem(myConstants
														.Translation()
														.toLowerCase()), null);
							else
								MessageBox.info(myConstants.Failure(),
										myMessages.DeleteFailed(myConstants
												.translations()), null);
						}
					};
					AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
							.create(AdministrationService.class);
					service.deleteTranslation(grid.getSelectionModel()
							.getSelectedItem().getTranslationDTO(), callback);
				}

				if (grid.getStore().getCount() == 0) {
					ce.getComponent().disable();
				}
			}

		});
		// btn.setIcon(Resources.ICONS.delete());
		ContentPanel cp = new ContentPanel();
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setHeading(myMessages.ListOf(myConstants.translations()));
		cp.setLayout(new FitLayout());
		cp.setSize(700, 300);
		cp.add(grid);
		cp.addButton(removeButton);
		grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");

		verticalPanel.add(cp);
	}

	private void createButton() {
		createButton = new Button(myConstants.Create(),
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						AsyncCallback<TranslationDTO> callback = new AsyncCallback<TranslationDTO>() {

							@Override
							public void onSuccess(TranslationDTO result) {

								if (!removeButton.isEnabled())
									removeButton.enable();

								grid.getStore().add(
										new TranslationModel(result));

								MessageBox.info(myConstants.Success(),
										myConstants.CreateSuccess(), null);
							}

							@Override
							public void onFailure(Throwable caught) {
								MessageBox.info(myConstants.Failure(),
										myMessages.CreateFailedFem(myConstants
												.translations()), null);
							}
						};
						TranslationDTO translation = new TranslationDTO();
						PriceDTO price = new PriceDTO(defaultPriceField
								.getValue().intValue(), currencyCombo
								.getValue().getCurrencyDTO());
						translation.setDefaultPrice(price);
						translation.setFrom(fromCombo.getValue()
								.getLanguageDTO());
						translation.setTo(toCombo.getValue().getLanguageDTO());
						translation.setType(translationTypeCombo.getValue()
								.getTranslationTypeDTO());
						AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
								.create(AdministrationService.class);
						service.createTranslation(translation, callback);
					}
				});

		createForm.addButton(createButton);
		createForm.setButtonAlign(HorizontalAlignment.CENTER);
		FormButtonBinding binding = new FormButtonBinding(createForm);
		binding.addButton(createButton);
	}

	private void createNewTranslationForm() {
		createForm = new FormPanel();
		createForm
				.setHeading(myMessages.CreateNewFem(myConstants.translation()));
		createForm.setPadding(20);
		createForm.setLabelAlign(LabelAlign.LEFT);
		createForm.setLabelWidth(100);
		createLanguageCombos();
		createTranslationTypeCombo();
		createCurrencyCombo();
		createDefaultPriceField();
		verticalPanel.add(createForm);

	}

	private void createDefaultPriceField() {
		defaultPriceField = new NumberField();
		defaultPriceField.setFieldLabel(myConstants.DefaultPrice());
		defaultPriceField.setAllowBlank(false);
		createForm.add(defaultPriceField, formData);
	}

	private void createCurrencyCombo() {
		currencyCombo = new ComboBox<CurrencyModel>();
		currencyCombo.setFieldLabel(myConstants.Currency());
		currencyCombo.setEmptyText(myConstants.SelectCurrency());
		final ListStore<CurrencyModel> list = new ListStore<CurrencyModel>();
		AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

			@Override
			public void onSuccess(Set<CurrencyTypeDTO> result) {
				for (CurrencyTypeDTO currencyType : result)
					list.add(new CurrencyModel(currencyType));
			}

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.info(myConstants.Failure(),
						myMessages.UploadFailed(myConstants.currencyList()),
						null);
			}
		};

		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getCurrency(callback);

		currencyCombo.setDisplayField("currency");
		list.sort("currency", SortDir.ASC);

		currencyCombo.setStore(list);
		currencyCombo.setTypeAhead(true);
		currencyCombo.setTriggerAction(TriggerAction.ALL);
		currencyCombo.setAllowBlank(false);
		createForm.add(currencyCombo);

	}

	private void createTranslationTypeCombo() {
		translationTypeCombo = new ComboBox<TranslationTypeModel>();
		translationTypeCombo.setFieldLabel(myConstants.TranslationType());
		translationTypeCombo.setEmptyText(myConstants.SelectTranslationType());
		final ListStore<TranslationTypeModel> list = new ListStore<TranslationTypeModel>();
		AsyncCallback<Set<TranslationTypeDTO>> callback = new AsyncCallback<Set<TranslationTypeDTO>>() {

			@Override
			public void onSuccess(Set<TranslationTypeDTO> result) {
				for (TranslationTypeDTO translationType : result)
					list.add(new TranslationTypeModel(translationType));
			}

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.info(myConstants.Failure(), myMessages
						.UploadFailed(myConstants.translationTypeList()), null);
			}
		};

		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getTranslationTypes(callback);

		translationTypeCombo.setDisplayField("name");
		list.sort("unit", SortDir.ASC);

		translationTypeCombo.setStore(list);
		translationTypeCombo.setTypeAhead(true);
		translationTypeCombo.setTriggerAction(TriggerAction.ALL);
		translationTypeCombo.setAllowBlank(false);
		createForm.add(translationTypeCombo);
	}

	private void createLanguageCombos() {
		fromCombo = new ComboBox<LanguageModel>();
		fromCombo.setFieldLabel(myConstants.LanguageFrom());
		fromCombo.setEmptyText(myConstants.SelectLanguage());

		toCombo = new ComboBox<LanguageModel>();
		toCombo.setFieldLabel(myConstants.LanguageTo());
		toCombo.setEmptyText(myConstants.SelectLanguage());

		final ListStore<LanguageModel> list = new ListStore<LanguageModel>();
		AsyncCallback<Set<LanguageDTO>> callback = new AsyncCallback<Set<LanguageDTO>>() {

			@Override
			public void onSuccess(Set<LanguageDTO> result) {
				for (LanguageDTO language : result)
					list.add(new LanguageModel(language));
			}

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.info(myConstants.Failure(),
						myMessages.UploadFailed(myConstants.languages()), null);
			}
		};

		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getLanguages(callback);

		fromCombo.setDisplayField("lang");
		toCombo.setDisplayField("lang");
		list.sort("lang", SortDir.ASC);

		fromCombo.setStore(list);
		fromCombo.setTypeAhead(true);
		fromCombo.setTriggerAction(TriggerAction.ALL);
		fromCombo.setAllowBlank(false);

		toCombo.setStore(list);
		toCombo.setTypeAhead(true);
		toCombo.setTriggerAction(TriggerAction.ALL);
		toCombo.setAllowBlank(false);

		createForm.add(fromCombo);
		createForm.add(toCombo);
	}

	private void createVerticalPanel() {
		verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		verticalPanel.setSpacing(20);
	}

}
