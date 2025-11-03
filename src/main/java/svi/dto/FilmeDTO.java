package svi.dto;

import java.util.List;

import svi.model.Ator;
import svi.model.Genero;
import svi.model.Premio;

public record FilmeDTO(String titulo,String duracao,String sinopse,String classificacaoIndicativa,List<Premio> listaPremios,List<Genero> listaGeneros,List<Ator> listaAtores,Long idIdiomaOriginal) {

}
