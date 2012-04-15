package com.pengurus.crm.client.panels.center.user.create;

import com.pengurus.crm.shared.dto.PersonalDataDTO;

public class PersonalDataEdit extends PersonalDataBase{

    private StringSetPanel phoneNumber, email;
    
    public PersonalDataEdit(){
        super();
        addPhoneNumberField();
        addEmailField();
    }
    
    public PersonalDataEdit(PersonalDataDTO personalData) {
        super();
        fromPersonalData(personalData);
    }
    
    @Override
    protected void addPhoneNumberField() {
        phoneNumber = new StringSetPanel("Phone number", "phone number");
        add(phoneNumber);
    }

    @Override
    protected void addEmailField() {
        email = new StringSetPanel("E-mail adress", "e-mail adress");
        add(email);
    }

    @Override
    protected void fromPersonalData(PersonalDataDTO personalData) {
        super.fromPersonalData(personalData);
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
