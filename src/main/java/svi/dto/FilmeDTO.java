package svi.dto;

import java.util.List;




public record FilmeDTO(String titulo,
                       String duracao,
                       String sinopse,
                       String classificacaoIndicativa,
                       String premios,
                       String generos,
                       List<AtorDTO> Atores,
                       IdiomaDTO idioma_original) {

}
