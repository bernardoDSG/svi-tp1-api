package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.AtorDTO;
import svi.model.Ator;
import svi.repository.AtorRepository;

@ApplicationScoped
public class AtorServiceImpl implements AtorService{
    @Inject
    AtorRepository repository;

    @Override
    @Transactional
    public Ator create(AtorDTO dto) {
        Ator ator = new Ator();
        ator.setNome(dto.nome());
        ator.setPremios(dto.premios());

        repository.persist(ator);
        return ator;
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<Ator> findAll() {
        return repository.listAll();
    }

    @Override
    public Ator findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Ator> findByNome(String nome) {
        
        return repository.findByNome(nome);
    }

    @Override
    @Transactional
    public void update(Long id, AtorDTO dto) {
        Ator ator = repository.findById(id);
        ator.setNome(dto.nome());
        ator.setPremios(dto.premios());
        
    }

  
}
