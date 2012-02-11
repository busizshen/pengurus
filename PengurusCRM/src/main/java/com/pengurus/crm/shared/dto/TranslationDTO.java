package com.pengurus.crm.shared.dto;

import com.pengurus.crm.entities.Language;
import com.pengurus.crm.entities.Price;
import com.pengurus.crm.entities.TranslationType;

public class TranslationDTO {

    private TranslationType type;
    private Language from;
    private Language to;
    private Price defaultPrice;

    public TranslationDTO() {
        super();
    }

    public TranslationDTO(TranslationType type, Language from, Language to,
                       Price defaultPrice) {
        super();
        this.type = type;
        this.from = from;
        this.to = to;
        this.defaultPrice = defaultPrice;
    }

    public Price getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Price defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public TranslationType getType() {
        return type;
    }

    public void setType(TranslationType type) {
        this.type = type;
    }

    public Language getFrom() {
        return from;
    }

    public void setFrom(Language from) {
        this.from = from;
    }

    public Language getTo() {
        return to;
    }

    public void setTo(Language to) {
        this.to = to;
    }
    
}
