package svi.dto;

import java.util.List;

import svi.converter.ConverterIdObjeto;

import svi.model.Ator;

public record AtorDTO(String nome,List<Long> idPremios) {

    public static Ator fromDTO (AtorDTO dto) {
        ConverterIdObjeto converterId = new ConverterIdObjeto();
        Ator ator = new Ator();
        ator.setNome(dto.nome());
        ator.setPremios( dto.idPremios().stream().map(i ->converterId.premioFromId(i)).toList());
        return ator;
    }
}
