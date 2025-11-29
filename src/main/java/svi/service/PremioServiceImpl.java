package svi.service;

import java.util.Arrays;

import svi.converter.ConverterPremioString;
import svi.model.Premio;

public class PremioServiceImpl implements PremioService{

    @Override
    public String getPremios() {
        ConverterPremioString converter = new ConverterPremioString();
        return converter.converterPremioStringId( Arrays.asList(Premio.values())) ;
    }

}
