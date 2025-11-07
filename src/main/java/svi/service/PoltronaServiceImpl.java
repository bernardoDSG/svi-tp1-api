package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.PoltronaDTO;
import svi.dto.PoltronaDTOResponse;
import svi.model.Poltrona;
import svi.repository.PoltronaRepository;


@ApplicationScoped
public class PoltronaServiceImpl implements PoltronaService{
    @Inject
    PoltronaRepository repository;



    @Override
    @Transactional
    public PoltronaDTOResponse create(PoltronaDTO dto) {
        Poltrona poltrona = new Poltrona();
        poltrona.setNome(dto.nome());
        poltrona.setEstaOcupada(dto.estaOcupada());
        
        repository.persist(poltrona);
        
        return PoltronaDTOResponse.valueOf(poltrona);
    }

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<PoltronaDTOResponse> findAll() {
       return repository.listAll()
                        .stream()
                        .map(m -> PoltronaDTOResponse.valueOf(m))
                        .toList();
    }

    @Override
    public List<PoltronaDTOResponse> findByDisponibilidade(Boolean estaOcupada) {
        
        return repository.findByDisponibilidade(estaOcupada)
                        .stream()
                        .map(m -> PoltronaDTOResponse.valueOf(m))
                        .toList();
    }

    @Override
    public PoltronaDTOResponse findById(Long id) {
        
        Poltrona poltrona =  repository.findById(id);
        if(poltrona == null) {
            return null;
        }
        return PoltronaDTOResponse.valueOf(poltrona);
    }

    @Override
    public List<PoltronaDTOResponse> findByNome(String nome) {
        
        return repository.findByNome(nome)
                         .stream()
                         .map(m -> PoltronaDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional 
    public void update(Long id, PoltronaDTO dto) {
        Poltrona poltrona = repository.findById(id);
        poltrona.setNome(dto.nome());
        poltrona.setEstaOcupada(dto.estaOcupada());
    }
    
}   
