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
import com.pengurus.crm.client.models.LanguageModel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.LanguageDTO;

public class LanguagePanel extends MainPanel {

    private HorizontalPanel horizontalPanel;
    private FormPanel mainForm;
    private FormPanel createForm;
    private TextField<String> languageField;
    private FormData formData;
    private Button createButton;
    private Button removeButton;
    private Grid<LanguageModel> grid;

    public LanguagePanel() {
        createForm();
        createLanguageField();
        createButton();
        createLanguageGrid();
        addVerticalPanel();
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
                MessageBox.info("Failure", "Uploading languages has "
                        + "failed.", null);
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
        column.setHeader("Language");
        column.setWidth(200);
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        grid = new Grid<LanguageModel>(list, cm);

        grid.addPlugin(r);
        grid.getView().setForceFit(true);

        removeButton = new Button("Remove selected language",
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (grid.getSelectionModel().getSelectedItem() != null) {

                            AsyncCallback<LanguageDTO> callback = new AsyncCallback<LanguageDTO>() {

                                @Override
                                public void onSuccess(LanguageDTO result) {
                                    grid.getStore().remove(
                                            grid.getSelectionModel()
                                                    .getSelectedItem());

                                    MessageBox.info("Success",
                                            "You have succesfully deleted "
                                                    + result.getLanguage()
                                                    + " language.", null);
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
                            service.deleteLanguage(grid.getSelectionModel()
                                    .getSelectedItem().getLanguageDTO(),
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
        cp.setHeading("List of languages");
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
        mainForm.setHeading("Language panel");
        mainForm.setFrame(true);
        mainForm.setPadding(50);

        createForm = new FormPanel();
        createForm.setHeading("Create new language");
        createForm.setPadding(20);
        createForm.setLabelAlign(LabelAlign.LEFT);
    }

    private void createLanguageField() {
        languageField = new TextField<String>();
        languageField.setFieldLabel("Language");
        languageField.setAllowBlank(false);
        createForm.add(languageField, formData);
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
                        AsyncCallback<LanguageDTO> callback = new AsyncCallback<LanguageDTO>() {

                            @Override
                            public void onSuccess(LanguageDTO result) {
                                if (!removeButton.isEnabled())
                                    removeButton.enable();
                                grid.getStore().add(new LanguageModel(result));
                                MessageBox.info("Success",
                                        "You have succesfully created new language: "
                                                + result.getLanguage() + ".",
                                        null);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox
                                        .info("Failure",
                                                "Creating new currency has "
                                                        + "failed. The language is"
                                                        + " already on the list.",
                                                null);
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