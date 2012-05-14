package com.pengurus.crm.client.panels.center.administration.currency;

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
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.client.service.exceptions.DependencyException;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyTypePanel extends MainPanel {

	private VerticalPanel verticalPanel;
	private TextField<String> currencyType;
	private FormData formData;
	private Button createButton;
	private FormPanel createForm;
	private Button removeButton;
	private Grid<CurrencyModel> grid;

	public CurrencyTypePanel() {
		super(500);
		setHeading(myConstants.Currency());
		createForm();
		createCurrencyField();
		createButton();
		createCurrencyGrid();
		add(verticalPanel);
	}

	private void createCurrencyGrid() {
		final ListStore<CurrencyModel> list = new ListStore<CurrencyModel>();
		AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

			@Override
			public void onSuccess(Set<CurrencyTypeDTO> result) {
				for (CurrencyTypeDTO language : result)
					list.add(new CurrencyModel(language));
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
		list.sort("currency", SortDir.ASC);

		RowNumberer r = new RowNumberer();
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		configs.add(r);

		ColumnConfig column = new ColumnConfig();
		column.setId("currency");
		column.setHeader(myConstants.Currency());
		column.setWidth(200);
		configs.add(column);

		ColumnModel cm = new ColumnModel(configs);

		grid = new Grid<CurrencyModel>(list, cm);

		grid.addPlugin(r);
		grid.getView().setForceFit(true);

		removeButton = new Button(myMessages.RemoveSelectedFem(myConstants
				.currency()), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (grid.getSelectionModel().getSelectedItem() != null) {

					AsyncCallback<CurrencyTypeDTO> callback = new AsyncCallback<CurrencyTypeDTO>() {

						@Override
						public void onSuccess(CurrencyTypeDTO result) {
							grid.getStore().remove(
									grid.getSelectionModel().getSelectedItem());

							MessageBox.info(
									myConstants.Success(),
									myMessages.DeleteSuccess(
											myConstants.currency(),
											result.getCurrency()), null);
						}

						@Override
						public void onFailure(Throwable caught) {
							if (caught instanceof DependencyException)
								MessageBox.info(
										myConstants.Failure(),
										myMessages
												.DependencyExceptionFem(myConstants
														.Currency()
														.toLowerCase()), null);
							else
								MessageBox.info(myConstants.Failure(),
										myMessages.DeleteFailed(myConstants
												.currencyFailed()), null);
						}
					};
					AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
							.create(AdministrationService.class);
					service.deleteCurrency(grid.getSelectionModel()
							.getSelectedItem().getCurrencyDTO(), callback);
				}

				if (grid.getStore().getCount() == 0) {
					ce.getComponent().disable();
				}
			}

		});

		// btn.setIcon(Resources.ICONS.delete());
		ContentPanel cp = new ContentPanel();
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setHeading(myMessages.ListOf(myConstants.currencyList()));
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
		createForm.setHeading(myMessages.CreateNewFem(myConstants.currency()));
		createForm.setPadding(20);
		createForm.setLabelAlign(LabelAlign.LEFT);
	}

	private void createCurrencyField() {
		currencyType = new TextField<String>();
		currencyType.setFieldLabel(myConstants.Currency());
		currencyType.setAllowBlank(false);
		createForm.add(currencyType, formData);
	}

	private void createButton() {
		createButton = new Button(myConstants.Create(),
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						AsyncCallback<CurrencyTypeDTO> callback = new AsyncCallback<CurrencyTypeDTO>() {

							@Override
							public void onSuccess(CurrencyTypeDTO result) {
								if (!removeButton.isEnabled())
									removeButton.enable();
								grid.getStore().add(new CurrencyModel(result));
								MessageBox.info(
										myConstants.Success(),
										myMessages.CreateSuccessFem(
												myConstants.currency(),
												result.getCurrency()), null);
							}

							@Override
							public void onFailure(Throwable caught) {
								MessageBox.info(myConstants.Failure(),
										myMessages.CreateFailedFem(myConstants
												.currencyFailed()), null);
							}
						};

						CurrencyTypeDTO currency = new CurrencyTypeDTO(
								currencyType.getValue());
						AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
								.create(AdministrationService.class);
						service.createCurrency(currency, callback);
					}
				});

		createForm.addButton(createButton);
		createForm.setButtonAlign(HorizontalAlignment.CENTER);
		FormButtonBinding binding = new FormButtonBinding(createForm);
		binding.addButton(createButton);
	}
}
