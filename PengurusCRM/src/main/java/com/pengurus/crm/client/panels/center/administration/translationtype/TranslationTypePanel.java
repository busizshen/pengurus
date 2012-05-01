package com.pengurus.crm.client.panels.center.administration.translationtype;

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
import com.pengurus.crm.client.models.TranslationTypeModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.TranslationTypeDTO;

public class TranslationTypePanel extends MainPanel {

    private HorizontalPanel horizontalPanel;
    private FormPanel mainForm;
    private FormPanel createForm;
    private TextField<String> unitField;
    private TextField<String> descriptionField;
    private FormData formData;
    private Button createButton;
    private Button removeButton;
    private Grid<TranslationTypeModel> grid;

    public TranslationTypePanel() {
        createForm();
        createUnitField();
        createDescriptionField();
        createButton();
        createLanguageGrid();
        addVerticalPanel();
    }

    private void createLanguageGrid() {
        final ListStore<TranslationTypeModel> list = new ListStore<TranslationTypeModel>();
        AsyncCallback<Set<TranslationTypeDTO>> callback = new AsyncCallback<Set<TranslationTypeDTO>>() {

            @Override
            public void onSuccess(Set<TranslationTypeDTO> result) {
                for (TranslationTypeDTO translationType : result)
                    list.add(new TranslationTypeModel(translationType));
            }

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Failure", "Uploading translation types has "
                        + "failed.", null);
            }
        };

        AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
                .create(AdministrationService.class);
        service.getTranslationTypes(callback);

        list.sort("unit", SortDir.ASC);

        RowNumberer r = new RowNumberer();
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        configs.add(r);

        ColumnConfig column = new ColumnConfig();

        column.setId("unit");
        column.setHeader("Unit");
        column.setWidth(100);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("type");
        column.setHeader("Description");
        column.setWidth(300);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        grid = new Grid<TranslationTypeModel>(list, cm);
        grid.addPlugin(r);
        grid.getView().setForceFit(true);

        removeButton = new Button("Remove selected translation type",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (grid.getSelectionModel().getSelectedItem() != null) {

                            AsyncCallback<TranslationTypeDTO> callback = new AsyncCallback<TranslationTypeDTO>() {

                                @Override
                                public void onSuccess(TranslationTypeDTO result) {
                                    grid.getStore().remove(
                                            grid.getSelectionModel()
                                                    .getSelectedItem());

                                    MessageBox.info("Success",
                                            "You have succesfully deleted "
                                                    + result.getUnit() + " "
                                                    + result.getDescription()
                                                    + " translation type.",
                                            null);
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    MessageBox.info("Failure",
                                            "Deleting language has failed.",
                                            null);
                                }
                            };
                            AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
                                    .create(AdministrationService.class);
                            service.deleteTranslationType(grid
                                    .getSelectionModel().getSelectedItem()
                                    .getTranslationTypeDTO(), callback);
                        }

                        if (grid.getStore().getCount() == 0) {
                            ce.getComponent().disable();
                        }
                    }

                });
        // btn.setIcon(Resources.ICONS.delete());
        ContentPanel cp = new ContentPanel();
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        cp.setHeading("List of translation types");
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
        mainForm.setHeading("Translation type panel");
        mainForm.setFrame(true);
        mainForm.setPadding(50);

        createForm = new FormPanel();
        createForm.setHeading("Create new translation type");
        createForm.setPadding(20);
        createForm.setLabelAlign(LabelAlign.LEFT);
    }

    private void createUnitField() {
        unitField = new TextField<String>();
        unitField.setFieldLabel("Unit");
        unitField.setAllowBlank(false);
        createForm.add(unitField, formData);
    }

    private void createDescriptionField() {
        descriptionField = new TextField<String>();
        descriptionField.setFieldLabel("Description");
        descriptionField.setAllowBlank(false);
        createForm.add(descriptionField, formData);
    }

    private void addVerticalPanel() {
        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(20);
        horizontalPanel.add(mainForm);
        add(horizontalPanel);
    }

    private void createButton() {
        createButton = new Button("Create",
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        AsyncCallback<TranslationTypeDTO> callback = new AsyncCallback<TranslationTypeDTO>() {

                            @Override
                            public void onSuccess(TranslationTypeDTO result) {
                                if (!removeButton.isEnabled())
                                    removeButton.enable();
                                grid.getStore().add(
                                        new TranslationTypeModel(result));
                                MessageBox.info("Success",
                                        "You have succesfully created new translation type: "
                                        + result.getUnit() + "-"
                                        + result.getDescription()
                                        + ".", null);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox.info("Failure",
                                        "Creating new translation type has "
                                                + "failed.", null);
                            }
                        };
                        TranslationTypeDTO translationType = new TranslationTypeDTO(
                                descriptionField.getValue(), unitField
                                        .getValue());
                        AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
                                .create(AdministrationService.class);
                        service.createTranslationType(translationType, callback);
                    }
                });

        createForm.addButton(createButton);
        createForm.setButtonAlign(HorizontalAlignment.CENTER);
        FormButtonBinding binding = new FormButtonBinding(createForm);
        binding.addButton(createButton);
    }

}
