package svi.service;

import java.util.List;

import svi.dto.AtorDTO;
import svi.model.Ator;



public interface AtorService {
    List<Ator> findAll();
    List<Ator> findByNome(String nome);
    Ator findById(Long id);
    Ator create (AtorDTO dto);
    void update (Long id, AtorDTO dto);
    void delete (long id);
}
