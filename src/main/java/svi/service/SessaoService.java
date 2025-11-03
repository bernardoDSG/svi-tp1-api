package svi.service;

import java.util.List;

import svi.dto.SessaoDTO;
import svi.model.Sessao;

public interface SessaoService {
    List<Sessao> findAll();
    Sessao findById(Long id);
    Sessao create(SessaoDTO dto);
    void update(Long id, SessaoDTO dto);
    void delete(Long id);
}
