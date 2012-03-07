package com.pengurus.crm.client.panels.center.administration.language;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.pengurus.crm.client.panels.center.MainPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.LanguageDTO;

public class LanguageCreatePanel extends MainPanel {

    private HorizontalPanel horizontalPanel;
    private FormPanel form;
    private TextField<String> languageField;
    private FormData formData;
    private Button createButton;

    private LanguageDeleteForm deleteForm;

    public LanguageCreatePanel() {
        createForm();
        createLanguageField();
        createButton();
        createDeleteForm();
        addVerticalPanel();
    }

    private void createForm() {
        form = new FormPanel();
        form.setHeading("Create language");
        form.setFrame(true);
        form.setPadding(25);
        form.setLabelAlign(LabelAlign.TOP);
    }

    private void createLanguageField() {
        languageField = new TextField<String>();
        languageField.setFieldLabel("Language");
        languageField.setAllowBlank(false);
        form.add(languageField, formData);
    }

    private void addVerticalPanel() {
        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setSpacing(20);
        horizontalPanel.add(form);
        horizontalPanel.add(deleteForm);
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
                                deleteForm.addComboField(result);
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

        form.addButton(createButton);
        form.setButtonAlign(HorizontalAlignment.CENTER);
        FormButtonBinding binding = new FormButtonBinding(form);
        binding.addButton(createButton);
    }

    private void createDeleteForm() {
        deleteForm = new LanguageDeleteForm();
        deleteForm.setHeading("Delete language");
        deleteForm.setFrame(true);
        deleteForm.setPadding(25);
        deleteForm.setLabelAlign(LabelAlign.TOP);
    }

}
