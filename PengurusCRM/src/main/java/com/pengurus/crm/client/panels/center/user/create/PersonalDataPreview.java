package com.pengurus.crm.client.panels.center.user.create;

import com.pengurus.crm.shared.dto.PersonalDataDTO;

public class PersonalDataPreview extends PersonalDataBase {
    
private StringGrid phoneNumber, email;
    
    public PersonalDataPreview(){
        super();
        addPhoneNumberField();
        addEmailField();
    }
    
    public PersonalDataPreview(PersonalDataDTO personalData) {
        super();
        fromPersonalData(personalData);
    }
    
    @Override
    protected void addPhoneNumberField() {
        phoneNumber = new StringGrid("Phone numbers", "phone number");
        add(phoneNumber);
    }

    @Override
    protected void addEmailField() {
        email = new StringGrid("E-mail adress", "e-mail adress");
        add(email);
    }

    @Override
    protected void fromPersonalData(PersonalDataDTO personalData) {
    	super.fromPersonalData(personalData);
        firstName.setReadOnly(true);
        lastName.setReadOnly(true);
        address.setReadOnly(true);
        phoneNumber.setData(personalData.getPhoneNumber());
        email.setData(personalData.getEmail());
    }

    @Override
    public PersonalDataDTO toPersonalData() {
        return new PersonalDataDTO(id, firstName.getValue(),
                lastName.getValue(), address.getValue(), phoneNumber.getData(),
                email.getData());
    }


}
