package svi.service;

import java.util.List;

import svi.dto.PoltronaDTO;
import svi.model.Poltrona;

public interface PoltronaService {
    List<Poltrona> findAll();
    List<Poltrona> findByNome(String nome);
    List<Poltrona> findByDisponibilidade(Boolean estaOcupada);
    Poltrona findById(Long id);
    Poltrona create (PoltronaDTO dto);
    void update (Long id, PoltronaDTO dto);
    void delete (Long id);
}
