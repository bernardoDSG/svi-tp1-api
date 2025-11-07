package svi.service;

import java.util.List;

import svi.dto.PoltronaDTO;
import svi.dto.PoltronaDTOResponse;


public interface PoltronaService {
    List<PoltronaDTOResponse> findAll();
    List<PoltronaDTOResponse> findByNome(String nome);
    List<PoltronaDTOResponse> findByDisponibilidade(Boolean estaOcupada);
    PoltronaDTOResponse findById(Long id);
    PoltronaDTOResponse create (PoltronaDTO dto);
    void update (Long id, PoltronaDTO dto);
    void delete (Long id);
}
