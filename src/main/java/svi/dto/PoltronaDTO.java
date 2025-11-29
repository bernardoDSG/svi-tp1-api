package svi.dto;

import svi.model.Poltrona;

public record PoltronaDTO(String nome) {

        public static Poltrona fromDTO (PoltronaDTO dto) {
            Poltrona poltrona = new Poltrona();
            poltrona.setNome(dto.nome());
            return poltrona;
        }
}
