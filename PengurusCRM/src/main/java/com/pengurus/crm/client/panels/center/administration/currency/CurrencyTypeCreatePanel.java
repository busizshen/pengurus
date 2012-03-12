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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyTypeCreatePanel extends MainPanel {

    private HorizontalPanel horizontalPanel;
    private TextField<String> currencyType;
    private FormData formData;
    private Button createButton;
    private FormPanel mainForm;
    private FormPanel createForm;
    private Button removeButton;
    private Grid<CurrencyModel> grid;

    public CurrencyTypeCreatePanel() {
        createForm();
        createCurrencyField();
        createButton();
        createCurrencyGrid();
        addVerticalPanel();
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
                MessageBox.info("Failure", "Uploading currencies has "
                        + "failed.", null);
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
        column.setHeader("Currency");
        column.setWidth(200);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        grid = new Grid<CurrencyModel>(list, cm);

        grid.addPlugin(r);
        grid.getView().setForceFit(true);

        removeButton = new Button("Remove selected currency",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (grid.getSelectionModel().getSelectedItem() != null) {

                            AsyncCallback<CurrencyTypeDTO> callback = new AsyncCallback<CurrencyTypeDTO>() {

                                @Override
                                public void onSuccess(CurrencyTypeDTO result) {
                                    grid.getStore().remove(
                                            grid.getSelectionModel()
                                                    .getSelectedItem());

                                    MessageBox.info("Success",
                                            "You have succesfully deleted "
                                                    + result.getCurrency()
                                                    + " currency.", null);
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    MessageBox.info("Failure",
                                            "Deleting currency has failed.",
                                            null);
                                }
                            };
                            AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
                                    .create(AdministrationService.class);
                            service.deleteCurrency(grid.getSelectionModel()
                                    .getSelectedItem().getCurrencyDTO(),
                                    callback);
                        }

                        if (grid.getStore().getCount() == 0) {
                            ce.getComponent().disable();
                        }
                    }

                });

        // btn.setIcon(Resources.ICONS.delete());
        ContentPanel cp = new ContentPanel();
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        cp.setHeading("List of currencies");
        cp.setLayout(new FitLayout());
        cp.setSize(450, 300);
        cp.add(grid);
        cp.addButton(removeButton);
        grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");

        mainForm.add(createForm);
        mainForm.add(cp);

    }

    private void createForm() {
        mainForm = new FormPanel();
        mainForm.setHeading("Currency panel");
        mainForm.setFrame(true);
        mainForm.setPadding(50);

        createForm = new FormPanel();
        createForm.setHeading("Create new currency");
        createForm.setPadding(20);
        createForm.setLabelAlign(LabelAlign.TOP);
    }

    private void createCurrencyField() {
        currencyType = new TextField<String>();
        currencyType.setFieldLabel("Currency");
        currencyType.setAllowBlank(false);
        createForm.add(currencyType, formData);
    }

    private void addVerticalPanel() {
        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(20);
        horizontalPanel.add(mainForm);
        // horizontalPanel.add(deleteForm);
        add(horizontalPanel);
    }

    private void createButton() {
        createButton = new Button("Create",
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        AsyncCallback<CurrencyTypeDTO> callback = new AsyncCallback<CurrencyTypeDTO>() {

                            @Override
                            public void onSuccess(CurrencyTypeDTO result) {
                                if (!removeButton.isEnabled())
                                    removeButton.enable();
                                grid.getStore().add(new CurrencyModel(result));
                                MessageBox.info("Success",
                                        "You have succesfully created new "
                                                + result.getCurrency()
                                                + " currency.", null);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox
                                        .info("Failure",
                                                "Creating new currency has "
                                                        + "failed. The currency is"
                                                        + " already on the list.",
                                                null);
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
