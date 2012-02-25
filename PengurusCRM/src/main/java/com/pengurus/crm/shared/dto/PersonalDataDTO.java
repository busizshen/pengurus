package com.pengurus.crm.shared.dto;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonalDataDTO implements IsSerializable {
    
	private Long id;
	private String firstName;
    private String lastName;
    private String address;
    private Set<String> phoneNumber = new HashSet<String>();
    private Set<String> email = new HashSet<String>();
    
    public PersonalDataDTO(){
        super();
        phoneNumber = new HashSet<String>();
        email = new HashSet<String>();
    }
    
    public PersonalDataDTO(Long id, String firstName, String lastName, String address,
                        Set<String> phoneNumber, Set<String> email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}
	
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Set<String> getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(Set<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<String> getEmail() {
        return email;
    }

    public void setEmail(Set<String> email2) {
        this.email = email2;
    }
    
    
}
