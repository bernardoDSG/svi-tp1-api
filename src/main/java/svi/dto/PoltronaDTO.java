package svi.dto;

import svi.model.Poltrona;

public record PoltronaDTO(String nome,Boolean estaOcupada) {

        public static Poltrona fromDTO (PoltronaDTO dto) {
            Poltrona poltrona = new Poltrona();
            poltrona.setNome(dto.nome());
            poltrona.setEstaOcupada(dto.estaOcupada());
            return poltrona;
        }
}
