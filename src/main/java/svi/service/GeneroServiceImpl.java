package svi.service;

import java.util.Arrays;

import svi.converter.ConverterGeneroListString;
import svi.model.Genero;

public class GeneroServiceImpl implements GeneroService{

    @Override
    public String getGeneros() {
        ConverterGeneroListString converter = new ConverterGeneroListString();
        return converter.converterGeneroStringId(Arrays.asList(Genero.values()));
    }
    
}
