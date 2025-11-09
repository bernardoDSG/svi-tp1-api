package svi.dto;

import svi.model.Idioma;

public record IdiomaDTO(String nome, String sigla) {

        public static Idioma fromDTO (IdiomaDTO dto) {
            Idioma idioma = new Idioma();
            idioma.setNome(dto.nome());
            idioma.setSigla(dto.sigla());
            return idioma;
        }
}
