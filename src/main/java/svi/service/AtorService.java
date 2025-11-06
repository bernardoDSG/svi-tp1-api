package svi.service;

import java.util.List;

import svi.dto.AtorDTO;
import svi.dto.AtorDTOResponse;




public interface AtorService {
    List<AtorDTOResponse> findAll();
    List<AtorDTOResponse> findByNome(String nome);
    AtorDTOResponse findById(Long id);
    AtorDTOResponse create (AtorDTO dto);
    void update (Long id, AtorDTO dto);
    void delete (Long id);
}
