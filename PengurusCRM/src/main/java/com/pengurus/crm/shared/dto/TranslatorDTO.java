package com.pengurus.crm.shared.dto;

import java.util.List;
import java.util.Set;


public class TranslatorDTO extends WorkerDTO {

	private List<TranslationDTO> translations;

    public TranslatorDTO() {
        super();
    }

    public TranslatorDTO(Long id, Set<UserRoleDTO> permission, String login, String password,
                      String description, PersonalDataDTO data,
                      List<TranslationDTO> translations) {
        super(id, permission, login, password, description, data);
        this.translations = translations;
    }

    public List<TranslationDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationDTO> translations) {
        this.translations = translations;
    }
    
}
