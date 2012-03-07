package com.pengurus.crm.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TranslationDTO implements IsSerializable {

    private TranslationTypeDTO type;
    private LanguageDTO from;
    private LanguageDTO to;
    private PriceDTO defaultPrice;
    private Long Id;

    public TranslationDTO() {
        super();
    }

    public TranslationDTO(Long Id, TranslationTypeDTO type, LanguageDTO from, LanguageDTO to,
                       PriceDTO defaultPrice) {
        super();
        this.Id = Id;
        this.type = type;
        this.from = from;
        this.to = to;
        this.defaultPrice = defaultPrice;
    }

    public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public PriceDTO getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(PriceDTO defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public TranslationTypeDTO getType() {
        return type;
    }

    public void setType(TranslationTypeDTO type) {
        this.type = type;
    }

    public LanguageDTO getFrom() {
        return from;
    }

    public void setFrom(LanguageDTO from) {
        this.from = from;
    }

    public LanguageDTO getTo() {
        return to;
    }

    public void setTo(LanguageDTO to) {
        this.to = to;
    }
    
}
