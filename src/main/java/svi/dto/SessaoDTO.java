package svi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SessaoDTO(
    LocalDateTime horarioInicio,
    LocalDateTime horarioFim,
    Long idFilme,
    List<Long> idsSalas,
    Long idIdioma
) {}
