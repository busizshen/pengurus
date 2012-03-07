package com.pengurus.crm.client.panels.center.administration.language;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pengurus.crm.client.models.LanguageModel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.LanguageDTO;

public class LanguageDeleteForm extends FormPanel {

    private ComboBox<LanguageModel> combo;
    private Button deleteButton;

    public LanguageDeleteForm() {
        createCombo();
        createButton();
    }

    private void createCombo() {
        combo = new ComboBox<LanguageModel>();
        combo.setFieldLabel("Language");
        combo.setEmptyText("Select a language");

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

        combo.setDisplayField("lang");
        list.sort("lang", SortDir.ASC);
        combo.setStore(list);
        combo.setTypeAhead(true);
        combo.setTriggerAction(TriggerAction.ALL);
        combo.setAllowBlank(false);
        add(combo);
    }

    private void createButton() {
        deleteButton = new Button("Delete",
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        AsyncCallback<LanguageDTO> callback = new AsyncCallback<LanguageDTO>() {

                            @Override
                            public void onSuccess(LanguageDTO result) {

                                LanguageModel lang = null;
                                for (LanguageModel language : combo.getStore()
                                        .getModels())
                                    if (language.getLang().equals(
                                            result.getLanguage()))
                                        lang = language;
                                if (lang != null) {
                                    combo.getStore().remove(lang);
                                }

                                MessageBox.info("Success",
                                        "You have succesfully deleted "
                                                + result.getLanguage()
                                                + " language.", null);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox.info("Failure",
                                        "Deleting language has failed.", null);
                            }
                        };

                        AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
                                .create(AdministrationService.class);
                        service.deleteLanguage(combo.getValue()
                                .getLanguageDTO(), callback);
                    }
                });

        addButton(deleteButton);
        setButtonAlign(HorizontalAlignment.CENTER);
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(deleteButton);

    }

    public void addComboField(LanguageDTO language) {
        combo.getStore().add(new LanguageModel(language));
    }

}
