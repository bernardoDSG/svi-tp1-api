package svi.dto;

import java.util.List;

import svi.model.Ator;
import svi.model.Premio;

public record AtorDTOResponse(
    Long id,
    String nome,
    List<Premio> premios
) {

    public static AtorDTOResponse valueOf(Ator ator) {
        return new AtorDTOResponse(ator.getId()
        , ator.getNome()
        , ator.getPremios());
    }
}
