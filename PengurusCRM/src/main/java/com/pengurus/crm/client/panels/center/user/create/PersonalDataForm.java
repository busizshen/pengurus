package com.pengurus.crm.client.panels.center.user.create;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.pengurus.crm.shared.dto.PersonalDataDTO;

public class PersonalDataForm extends FormPanel {

	private Long id;
	private TextField<String> firstName, lastName, address;
	private StringSetPanel phoneNumber, email;

	public PersonalDataForm() {
		addFirstNameField();
		addLastNameField();
		addAddressField();
		addPhoneNumberField();
		addEmailField();
		setPadding(25);
	}

	public PersonalDataForm(PersonalDataDTO personalData) {
		this();
		fromPersonalData(personalData);
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

	private void addPhoneNumberField() {
		phoneNumber = new StringSetPanel("Phone number", "phone number");
		add(phoneNumber);
	}

	private void addEmailField() {
		email = new StringSetPanel("E-mail adress", "e-mail adress");
		add(email);
	}

	public void fromPersonalData(PersonalDataDTO personalData) {
		id = personalData.getId();
		firstName.setValue(personalData.getFirstName());
		lastName.setValue(personalData.getLastName());
		address.setValue(personalData.getAddress());
		phoneNumber.setData(personalData.getPhoneNumber());
		email.setData(personalData.getEmail());
	}

	public PersonalDataDTO toPersonalData() {
		return new PersonalDataDTO(id, firstName.getValue(),
				lastName.getValue(), address.getValue(), phoneNumber.getData(),
				email.getData());
	}
}
