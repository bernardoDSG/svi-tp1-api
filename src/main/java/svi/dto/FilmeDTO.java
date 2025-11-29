package svi.dto;

import java.util.List;




public record FilmeDTO(String titulo,
                       String duracao,
                       String sinopse,
                       String classificacaoIndicativa,
                       List<Long> idPremios,
                       List<Long> idGeneros,
                       List<Long> idAtores,
                       Long idIdiomaOriginal) {

}
