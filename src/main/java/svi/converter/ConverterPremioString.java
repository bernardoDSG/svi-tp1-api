package svi.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import svi.model.Premio;

@Converter
public class ConverterPremioString implements AttributeConverter<List<Premio> , String>{
    private static final String SEPARADOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Premio> listaPremios) {
        if(listaPremios == null) {
             return null;    
        }
        else {
            return 
            listaPremios.stream()
            .map(Premio :: getNome)
            .collect(Collectors.joining(SEPARADOR));
        } 
        
        
    }

    @Override
    public List<Premio> convertToEntityAttribute(String stringDePremios) {
        if(stringDePremios == null || stringDePremios.trim().isEmpty() == true) {
            return List.of();
        }
        else {
            return Arrays.stream(stringDePremios.split(SEPARADOR)).map(String :: trim).map(Premio :: fromNome).collect(Collectors.toList());
        }
       
    }
    
}
