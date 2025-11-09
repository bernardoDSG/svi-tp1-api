package svi.service;

import java.util.List;

import svi.dto.SalaDTO;
import svi.dto.SalaDTOResponse;


public interface SalaService {
    List<SalaDTOResponse> findAll();
    List<SalaDTOResponse> findByNome(String nome);
    SalaDTOResponse findById(Long id);
    SalaDTOResponse create(SalaDTO dto);
    void update(Long id, SalaDTO dto);
    void delete (Long id);
}
