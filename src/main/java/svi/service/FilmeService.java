package svi.service;

import java.util.List;

import svi.dto.FilmeDTO;
import svi.model.Filme;

public interface FilmeService {
    List<Filme> findAll();
    List<Filme> findByTitulo(String titulo);
    List<Filme> findByClassificacaoIndicativa(String classificacaoIndicativa);
    Filme findById(Long id);
    Filme create (FilmeDTO dto);
    void update(Long id,FilmeDTO dto);
    void delete(Long id);

}
