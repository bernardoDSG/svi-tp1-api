package svi.dto;

import svi.model.Idioma;

public record IdiomaDTOResponse(
    Long id,
    String nome,
    String sigla
) {

    public static IdiomaDTOResponse valueOf(Idioma idioma) {
        return new IdiomaDTOResponse(idioma.getId()
                                    ,idioma.getNome()
                                    , idioma.getSigla());
    }
}
