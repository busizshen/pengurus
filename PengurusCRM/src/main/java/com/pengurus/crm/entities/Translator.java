package com.pengurus.crm.entities;

import java.util.HashSet;
import java.util.Set;

import com.pengurus.crm.shared.dto.TranslationDTO;
import com.pengurus.crm.shared.dto.TranslatorDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;


public class Translator extends Worker{

	private Set<Translation> translations;
    public Translator() {
        super();
    }

    public Translator(Set<UserRoleDTO> permission, String login, String password,
                      String description, PersonalData data,
                      Set<Translation> translations) {
        super(permission, login, password, description, data);
        this.translations = translations;
    }

    protected void init(TranslatorDTO translatorDTO) {
    	super.init(translatorDTO);
    	translations = new HashSet<Translation>();
    	for (TranslationDTO translationDTO: translatorDTO.getTranslations()) {
    		translations.add(new Translation(translationDTO));
    	}
    }
    
    public Translator(TranslatorDTO translatorDTO) {
    	init(translatorDTO);
	}

	public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    protected void rewriteLazy(TranslatorDTO translatorDTO) {
    	super.rewrite(translatorDTO);
   }
    
    @Override
    public TranslatorDTO toDTO(){
    	TranslatorDTO translatorDTO = toDTOLazy();
    	for(Translation t : getTranslations())
    		translatorDTO.getTranslations().add(t.toDTO());
    	return translatorDTO; 
    }
    
    @Override
    public TranslatorDTO toDTOLazy(){
    	TranslatorDTO translatorDTO = new TranslatorDTO();
    	rewriteLazy(translatorDTO);
    	return translatorDTO; 
    }
   
}
