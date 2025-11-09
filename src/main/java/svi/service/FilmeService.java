package svi.service;

import java.util.List;

import svi.dto.FilmeDTO;
import svi.dto.FilmeDTOResponse;


public interface FilmeService {
    List<FilmeDTOResponse> findAll();
    List<FilmeDTOResponse> findByTitulo(String titulo);
    List<FilmeDTOResponse> findByClassificacaoIndicativa(String classificacaoIndicativa);
    FilmeDTOResponse findById(Long id);
    FilmeDTOResponse create (FilmeDTO dto);
    void update(Long id,FilmeDTO dto);
    void delete(Long id);

}
