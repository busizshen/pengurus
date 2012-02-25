package com.pengurus.crm.entities;

import com.pengurus.crm.shared.dto.TranslationTypeDTO;

public class TranslationType {
    
    private Long id; 
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public TranslationTypeDTO toDTO() {
		TranslationTypeDTO ttDTO = new TranslationTypeDTO();
		ttDTO.setDescription(description);
		ttDTO.setId(id);
		ttDTO.setUnit(unit);
		return ttDTO;
	}
    
}
