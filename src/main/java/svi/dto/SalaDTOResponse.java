package svi.dto;

import java.util.List;

import svi.model.Sala;

public record SalaDTOResponse(
    Long id,
    String nome,
    List<PoltronaDTOResponse> poltronas
) {
public static SalaDTOResponse valueOf(Sala sala) {
    if(sala == null) {
        return null;
    }
    List<PoltronaDTOResponse> dtoResponses = sala.getListaPoltrona().stream()
                                                                    .map(p -> PoltronaDTOResponse.valueOf(p))
                                                                    .toList();
    return new SalaDTOResponse(sala.getId(), sala.getNome(), dtoResponses);
                                                     
                                                     
}


}
