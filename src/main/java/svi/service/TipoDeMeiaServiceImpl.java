package svi.service;

import java.util.Arrays;

import jakarta.enterprise.context.ApplicationScoped;
import svi.converter.ConverterTipoDeMeiaString;

import svi.model.TipodeMeia;

@ApplicationScoped
public class TipoDeMeiaServiceImpl implements TipoDeMeiaService {

    @Override
    public String getTiposMeia() {
        ConverterTipoDeMeiaString converter = new ConverterTipoDeMeiaString();

        return converter.converterTipoMeiaStringId( Arrays.asList(TipodeMeia.values()));
    }
    
}
