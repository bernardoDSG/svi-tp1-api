package svi.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import svi.model.Genero;


@Converter
public class ConverterGeneroListString implements AttributeConverter<List<Genero>, String> {

    private static final String SEPARADOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Genero> listaGeneros) {
        if (listaGeneros == null || listaGeneros.isEmpty()) {
            return null;
        }
        return listaGeneros.stream()
                .map(Genero::getNome)
                .collect(Collectors.joining(SEPARADOR));
    }

    @Override
    public List<Genero> convertToEntityAttribute(String generosString) {
        if (generosString == null || generosString.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(generosString.split(SEPARADOR))
                .map(String::trim)
                .map(Genero::fromNome)
                .collect(Collectors.toList());
    }

    public String converterGeneroStringId (List<Genero> listaGeneros) {
        if(listaGeneros == null) {
             return null;    
        }
        else {
            return 
            listaGeneros.stream()
            .map(g -> g.getId() + "-"+g.getNome())
            .collect(Collectors.joining(SEPARADOR));
        } 
        
    }
}
