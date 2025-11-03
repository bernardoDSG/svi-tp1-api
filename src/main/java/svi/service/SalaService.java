package svi.service;

import java.util.List;

import svi.dto.SalaDTO;
import svi.model.Sala;

public interface SalaService {
    List<Sala> findAll();
    List<Sala> findByNome(String nome);
    Sala findById(Long id);
    Sala create(SalaDTO dto);
    void update(Long id, SalaDTO dto);
    void delete (Long id);
}
