package svi.dto;

import java.time.LocalDateTime;
import java.util.List;

import svi.model.Sessao;

public record SessaoDTOResponse(
    Long id,
    FilmeDTOResponse filme,
    IdiomaDTOResponse idioma,
    List <SalaDTOResponse> salas,
    LocalDateTime horarioInicio,
    LocalDateTime horarioFim

) 

{
    public static SessaoDTOResponse valueOf(Sessao sessao) {
        if(sessao == null) {
            return null;
        }
        else{
            return new SessaoDTOResponse(sessao.getId(),
                                     FilmeDTOResponse.valueOf(sessao.getFilme()),
                                     IdiomaDTOResponse.valueOf(sessao.getIdioma()),
                                     sessao.getSalas().stream().map(s -> SalaDTOResponse.valueOf(s)).toList(),
                                     sessao.getHorarioInicio(),
                                     sessao.getHorarioFim()); 
        }
           
    }
}
