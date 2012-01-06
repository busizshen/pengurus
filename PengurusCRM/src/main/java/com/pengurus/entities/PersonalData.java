package com.pengurus.entities;

import java.util.List;

public class PersonalData {
    
    private String firstName;
    private String lastName;
    private String address;
    private List<String> phoneNumber;
    private List<String> email;
    
    public PersonalData(){
        super();
    }
    
    public PersonalData(String firstName, String lastName, String address,
                        List<String> phoneNumber, List<String> email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
    
    public List<String> getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
    
    
}
