package com.pengurus.crm.client.panels.center.administration.currency;

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
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.service.CurrencyTypeService;
import com.pengurus.crm.client.service.CurrencyTypeServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyDeleteForm extends FormPanel {

    private ComboBox<CurrencyModel> combo;
    private Button deleteButton;

    public CurrencyDeleteForm() {
        createCombo();
        createButton();
    }

    private void createCombo() {
        combo = new ComboBox<CurrencyModel>();
        combo.setFieldLabel("Currency");
        combo.setEmptyText("Select a currency");

        final ListStore<CurrencyModel> list = new ListStore<CurrencyModel>();
        AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

            @Override
            public void onSuccess(Set<CurrencyTypeDTO> result) {
                for (CurrencyTypeDTO currency : result)
                    list.add(new CurrencyModel(currency));
            }

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Failure", "Uploading currencies has "
                        + "failed.", null);
            }
        };

        CurrencyTypeServiceAsync service = (CurrencyTypeServiceAsync) GWT
                .create(CurrencyTypeService.class);
        service.getCurrencyTypes(callback);

        combo.setDisplayField("currency");
        list.sort("currency", SortDir.ASC);
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
                        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                MessageBox
                                        .info("Success",
                                                "Your have succesfully deleted currency.",
                                                null);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox.info("Failure",
                                        "Deleting currency has failed.", null);
                            }
                        };

                        CurrencyTypeServiceAsync service = (CurrencyTypeServiceAsync) GWT
                                .create(CurrencyTypeService.class);
                        service.deleteCurrencyType(combo.getValue()
                                .getCurrencyDTO(), callback);
                    }
                });

        addButton(deleteButton);
        setButtonAlign(HorizontalAlignment.CENTER);
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(deleteButton);

    }
}
