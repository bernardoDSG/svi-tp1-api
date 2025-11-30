package svi.service;

import java.util.List;

import svi.dto.SessaoDTO;
import svi.dto.SessaoDTOResponse;


public interface SessaoService {
    List<SessaoDTOResponse> findAll();
    SessaoDTOResponse findById(Long id);
    SessaoDTOResponse create(SessaoDTO dto);
    void update(Long id, SessaoDTO dto);
    void delete(Long id);
}
