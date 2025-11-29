package svi.service;

import java.util.Arrays;

import jakarta.enterprise.context.ApplicationScoped;
import svi.converter.ConverterGeneroListString;
import svi.model.Genero;

@ApplicationScoped
public class GeneroServiceImpl implements GeneroService{

    @Override
    public String getGeneros() {
        ConverterGeneroListString converter = new ConverterGeneroListString();
        return converter.converterGeneroStringId(Arrays.asList(Genero.values()));
    }
    
}
