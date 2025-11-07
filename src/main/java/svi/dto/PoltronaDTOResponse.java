package svi.dto;

import svi.model.Poltrona;

public record PoltronaDTOResponse(
    Long id,
    String nome,
    Boolean estaOcupada
) {
    public static PoltronaDTOResponse valueOf(Poltrona poltrona) {
        return new PoltronaDTOResponse(poltrona.getId()
                                      ,poltrona.getNome()
                                      ,poltrona.getEstaOcupada());
    }
}
