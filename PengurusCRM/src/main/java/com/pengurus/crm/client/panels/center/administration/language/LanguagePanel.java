package com.pengurus.crm.client.panels.center.administration.language;

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
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.LanguageModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.exceptions.DependencyException;
import com.pengurus.crm.shared.dto.LanguageDTO;

public class LanguagePanel extends MainPanel {

	private VerticalPanel verticalPanel;
	private FormPanel createForm;
	private TextField<String> languageField;
	private FormData formData;
	private Button createButton;
	private Button removeButton;
	private Grid<LanguageModel> grid;

	public LanguagePanel() {
		super(500);
		setHeading(myConstants.Languages());
		createForm();
		createLanguageField();
		createButton();
		createLanguageGrid();
		add(verticalPanel);
	}

	private void createLanguageGrid() {
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
		list.sort("lang", SortDir.ASC);

		RowNumberer r = new RowNumberer();
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		configs.add(r);

		ColumnConfig column = new ColumnConfig();
		column.setId("lang");
		column.setHeader(myConstants.Language());
		column.setWidth(200);
		configs.add(column);

		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<LanguageModel>(list, cm);

		grid.addPlugin(r);
		grid.getView().setForceFit(true);

		removeButton = new Button(myMessages.RemoveSelected(myConstants
				.language()), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItem() != null) {

					AsyncCallback<LanguageDTO> callback = new AsyncCallback<LanguageDTO>() {

						@Override
						public void onSuccess(LanguageDTO result) {
							grid.getStore().remove(
									grid.getSelectionModel().getSelectedItem());

							MessageBox.info(
									myConstants.Success(),
									myMessages.DeleteSuccess(
											myConstants.language(),
											result.getLanguage()), null);
						}

						@Override
						public void onFailure(Throwable caught) {
							if (caught instanceof DependencyException)
								MessageBox.info(
										myConstants.Failure(),
										myMessages
												.DependencyException(myConstants
														.language()), null);
							else
								MessageBox.info(myConstants.Failure(),
										myMessages.DeleteFailed(myConstants
												.languageFailed()), null);
						}
					};
					AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
							.create(AdministrationService.class);
					service.deleteLanguage(grid.getSelectionModel()
							.getSelectedItem().getLanguageDTO(), callback);
				}

				if (grid.getStore().getCount() == 0) {
					ce.getComponent().disable();
				}
			}

		});

		ContentPanel cp = new ContentPanel();
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setHeading(myMessages.ListOf(myConstants.languages()));
		cp.setLayout(new FitLayout());
		cp.setSize(450, 300);
		cp.add(grid);
		cp.addButton(removeButton);
		grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");

		verticalPanel.add(createForm);
		verticalPanel.add(cp);

	}

	private void createForm() {
		verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		verticalPanel.setSpacing(20);

		createForm = new FormPanel();
		createForm.setHeading(myMessages.CreateNew(myConstants.language()));
		createForm.setPadding(20);
		createForm.setLabelAlign(LabelAlign.LEFT);
	}

	private void createLanguageField() {
		languageField = new TextField<String>();
		languageField.setFieldLabel(myConstants.Language());
		languageField.setAllowBlank(false);
		createForm.add(languageField, formData);
	}

	private void createButton() {
		createButton = new Button(myConstants.Create(),
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						AsyncCallback<LanguageDTO> callback = new AsyncCallback<LanguageDTO>() {

							@Override
							public void onSuccess(LanguageDTO result) {
								if (!removeButton.isEnabled())
									removeButton.enable();
								grid.getStore().add(new LanguageModel(result));
								MessageBox.info(
										myConstants.Success(),
										myMessages.CreateSuccess(
												myConstants.language(),
												result.getLanguage()), null);
							}

							@Override
							public void onFailure(Throwable caught) {
								MessageBox.info(myConstants.Failure(),
										myMessages.CreateFailed(myConstants
												.languageFailed()), null);
							}
						};

						LanguageDTO language = new LanguageDTO(languageField
								.getValue());
						AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
								.create(AdministrationService.class);
						service.createLanguage(language, callback);
					}
				});

		createForm.addButton(createButton);
		createForm.setButtonAlign(HorizontalAlignment.CENTER);
		FormButtonBinding binding = new FormButtonBinding(createForm);
		binding.addButton(createButton);
	}

}