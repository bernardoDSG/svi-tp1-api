package svi.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import svi.model.TipodeMeia;

@Converter
public class ConverterTipoDeMeiaString implements AttributeConverter<TipodeMeia , String>{

    @Override
    public String convertToDatabaseColumn(TipodeMeia tipoDeMeia) {
        if(tipoDeMeia == null) {
            return null;
        }
        else {
            return tipoDeMeia.getNome();
        }
    }

    @Override
    public TipodeMeia convertToEntityAttribute(String stringTipoDeMeia) {
        if(stringTipoDeMeia == null || stringTipoDeMeia.trim().isEmpty() == true) {
            throw new IllegalArgumentException("enum tipo de meia nao encontrado para a string fornecida.");
        }
        else {
            TipodeMeia tipodeMeia = TipodeMeia.fromNome(stringTipoDeMeia);
            return tipodeMeia;
        }
        
    }
    
}
