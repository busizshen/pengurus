package com.pengurus.crm.entities;

public class TranslationType {
    
    private String description;
    private String unit;

    public TranslationType() {
        super();
    }

    public TranslationType(String description, String unit) {
        super();
        this.description = description;
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
       
    

}
