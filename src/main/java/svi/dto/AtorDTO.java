package svi.dto;

import svi.converter.ConverterPremioString;
import svi.model.Ator;

public record AtorDTO(String nome,String premios) {

    public static Ator fromDTO (AtorDTO dto) {
        ConverterPremioString converterPS = new ConverterPremioString();
        Ator ator = new Ator();
        ator.setNome(dto.nome());
        ator.setPremios(converterPS.convertToEntityAttribute(dto.premios()));
        return ator;
    }
}
