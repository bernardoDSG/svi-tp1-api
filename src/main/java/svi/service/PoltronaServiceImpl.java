package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.PoltronaDTO;
import svi.model.Poltrona;
import svi.repository.PoltronaRepository;


@ApplicationScoped
public class PoltronaServiceImpl implements PoltronaService{
    @Inject
    PoltronaRepository repository;



    @Override
    @Transactional
    public Poltrona create(PoltronaDTO dto) {
        Poltrona poltrona = new Poltrona();
        poltrona.setNome(dto.nome());
        poltrona.setEstaOcupada(dto.estaocupada());
        
        repository.persist(poltrona);
        
        return poltrona;
    }

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<Poltrona> findAll() {
       return repository.listAll();
    }

    @Override
    public List<Poltrona> findByDisponibilidade(Boolean estaOcupada) {
        
        return repository.findByDisponibilidade(estaOcupada);
    }

    @Override
    public Poltrona findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    public List<Poltrona> findByNome(String nome) {
        
        return repository.findByNome(nome);
    }

    @Override
    @Transactional 
    public void update(Long id, PoltronaDTO dto) {
        Poltrona poltrona = repository.findById(id);
        poltrona.setNome(dto.nome());
        poltrona.setEstaOcupada(dto.estaocupada());
    }
    
}   
