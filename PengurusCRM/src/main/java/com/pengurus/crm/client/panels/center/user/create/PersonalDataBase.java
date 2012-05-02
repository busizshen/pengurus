package com.pengurus.crm.client.panels.center.user.create;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.pengurus.crm.shared.dto.PersonalDataDTO;

public abstract class PersonalDataBase extends FormPanel {

    protected Long id;
    protected TextField<String> firstName, lastName, address;
    
    public PersonalDataBase() {
        addFirstNameField();
        addLastNameField();
        addAddressField();
        setPadding(25);
    }
    
    private void addFirstNameField() {
        firstName = new TextField<String>();
        firstName.setFieldLabel("First name");
        firstName.setAllowBlank(false);
        add(firstName);
    }

    private void addLastNameField() {
        lastName = new TextField<String>();
        lastName.setFieldLabel("Second name");
        lastName.setAllowBlank(false);
        add(lastName);
    }

    private void addAddressField() {
        address = new TextField<String>();
        address.setFieldLabel("Address");
        address.setAllowBlank(false);
        add(address);
    }
    
    protected void fromPersonalData(PersonalDataDTO personalData){
    	id = personalData.getId();
        firstName.setValue(personalData.getFirstName());
        lastName.setValue(personalData.getLastName());
        address.setValue(personalData.getAddress());
    }
    
    protected abstract void addPhoneNumberField();
    
    protected abstract void addEmailField();
     
    public abstract PersonalDataDTO toPersonalData();
}
