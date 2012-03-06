package com.pengurus.crm.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pengurus.crm.client.service.CurrencyTypeService;
import com.pengurus.crm.daos.CurrencyTypeDAO;
import com.pengurus.crm.entities.CurrencyType;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;

public class CurrencyTypeServiceImpl implements CurrencyTypeService {

    private CurrencyTypeDAO currencyTypeDAO;

    protected static final Logger log = LoggerFactory
            .getLogger(CurrencyTypeServiceImpl.class);

    public void setCurrencyTypeDAO(CurrencyTypeDAO currencyTypeDAO) {
        this.currencyTypeDAO = currencyTypeDAO;
    }

    public CurrencyTypeDAO getCurrencyTypeDAO() {
        return currencyTypeDAO;
    }    
    
    @Override
    public Set<CurrencyTypeDTO> getCurrencyTypes() {
        List<CurrencyType> list = currencyTypeDAO.loadAll();
        Set<CurrencyTypeDTO> currencyTypesDTO = new HashSet<CurrencyTypeDTO>();
        for (CurrencyType currencyType : list)
            currencyTypesDTO.add(currencyType.toDTO());
        return currencyTypesDTO;
    }

    @Override
    public void createCurrencyType(CurrencyTypeDTO currencyTypeDTO) {
        CurrencyType currency = new CurrencyType(currencyTypeDTO.getCurrency());
        currencyTypeDAO.create(currency);
    }

    @Override
    public void deleteCurrencyType(CurrencyTypeDTO currencyTypeDTO) {
        currencyTypeDAO.delete(new CurrencyType(currencyTypeDTO));
    }

}
