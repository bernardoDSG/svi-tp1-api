package svi.service;

import java.util.List;

import svi.dto.IdiomaDTO;
import svi.dto.IdiomaDTOResponse;


public interface IdiomaService {
    List<IdiomaDTOResponse> findAll();
    List<IdiomaDTOResponse> findByNome(String nome);
    List<IdiomaDTOResponse> findBySigla(String sigla);
    IdiomaDTOResponse findById (Long id);
    IdiomaDTOResponse create (IdiomaDTO dto);
    void update (Long id, IdiomaDTO dto);
    void delete (Long id);
}
