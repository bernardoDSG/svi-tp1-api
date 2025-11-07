package svi.dto;

import java.util.List;

import svi.model.Poltrona;
import svi.model.Sala;

public record SalaDTOResponse(
    Long id,
    String nome,
    List<Poltrona> listaPoltrona
) {
public static SalaDTOResponse valueOf(Sala sala) {
    return new SalaDTOResponse(sala.getId(), sala.getNome(), sala.getListaPoltrona());
}

}
