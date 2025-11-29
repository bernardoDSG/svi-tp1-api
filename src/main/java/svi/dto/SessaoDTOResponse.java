package svi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SessaoDTOResponse(
    Long id,
    FilmeDTOResponse filmeResponse,
    IdiomaDTOResponse idiomaResponse,
    List <SalaDTOResponse> salasResponse,
    LocalDateTime horarioInicio,
    LocalDateTime horarioFim

) 

{

}
