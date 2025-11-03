package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.IdiomaDTO;
import svi.model.Idioma;
import svi.repository.IdiomaRepository;

@ApplicationScoped
public class IdiomaServiceImpl implements IdiomaService{
    @Inject
    IdiomaRepository repository;

    @Override
    @Transactional
    public Idioma create(IdiomaDTO dto) {
        Idioma idioma = new Idioma();
        idioma.setNome(dto.nome());
        idioma.setSigla(dto.sigla());

        repository.persist(idioma);

        return idioma;
    }

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<Idioma> findAll() {
        return repository.listAll();
    }

    @Override
    public Idioma findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    public List<Idioma> findByNome(String nome) {
        
        return repository.findByNome(nome);
    }

    @Override
    public List<Idioma> findBySigla(String sigla) {
        
        return repository.findBySigla(sigla);
    }

    @Override
    @Transactional 
    public void update(Long id, IdiomaDTO dto) {
        Idioma idioma = repository.findById(id);
        idioma.setNome(dto.nome());
        idioma.setSigla(dto.sigla());
        
    }
    
}
