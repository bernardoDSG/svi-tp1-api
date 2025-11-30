package svi.dto;

import java.util.List;

import svi.converter.ConverterGeneroListString;
import svi.converter.ConverterPremioString;
import svi.model.Filme;


public record FilmeDTOResponse(
    Long id,
    String titulo,
    String duracao,
    String sinopse,
    String classificacaoIndicativa,
    String premios,
    String generos,
    List<AtorDTOResponse> atores,
    IdiomaDTOResponse idioma_original
) {
    public static FilmeDTOResponse valueOf(Filme filme) {
        ConverterPremioString converterPS = new ConverterPremioString();
        ConverterGeneroListString converterGS = new ConverterGeneroListString();
        return new FilmeDTOResponse(
            filme.getId(),
            filme.getTitulo(),
            filme.getDuracao(),
            filme.getSinopse(),
            filme.getClassificacaoIndicativa(),
            converterPS.convertToDatabaseColumn(filme.getListaPremios()),
            converterGS.convertToDatabaseColumn(filme.getListaGeneros()),
            filme.getListaAtores().stream().map(a -> AtorDTOResponse.valueOf(a)).toList(),
            IdiomaDTOResponse.valueOf(filme.getIdioma_original()) );
    }

}
