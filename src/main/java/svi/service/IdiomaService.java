package svi.service;

import java.util.List;

import svi.dto.IdiomaDTO;
import svi.model.Idioma;

public interface IdiomaService {
    List<Idioma> findAll();
    List<Idioma> findByNome(String nome);
    List<Idioma> findBySigla(String sigla);
    Idioma findById (Long id);
    Idioma create (IdiomaDTO dto);
    void update (Long id, IdiomaDTO dto);
    void delete (Long id);
}
