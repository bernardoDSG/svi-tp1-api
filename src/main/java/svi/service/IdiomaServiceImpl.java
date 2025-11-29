package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.IdiomaDTO;
import svi.dto.IdiomaDTOResponse;
import svi.model.Idioma;
import svi.repository.IdiomaRepository;

@ApplicationScoped
public class IdiomaServiceImpl implements IdiomaService{
    @Inject
    IdiomaRepository repository;

    @Override
    @Transactional
    public IdiomaDTOResponse create(IdiomaDTO dto) {
        Idioma idioma = new Idioma();
        idioma.setNome(dto.nome());
        idioma.setSigla(dto.sigla());

        repository.persist(idioma);

        return IdiomaDTOResponse.valueOf(idioma);
    }

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public List<IdiomaDTOResponse> findAll() {
        return repository.listAll()
                         .stream()
                         .map(m -> IdiomaDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional
    public IdiomaDTOResponse findById(Long id) {
        
        Idioma idioma = repository.findById(id);
        if(idioma == null) {
            return null;
        }
        return IdiomaDTOResponse.valueOf(idioma);
    }

    @Override
    @Transactional
    public List<IdiomaDTOResponse> findByNome(String nome) {
        
        return repository.findByNome(nome)
                         .stream()
                         .map(m -> IdiomaDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional
    public List<IdiomaDTOResponse> findBySigla(String sigla) {
        
        return repository.findBySigla(sigla)
                         .stream()
                         .map(m -> IdiomaDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional 
    public void update(Long id, IdiomaDTO dto) {
        Idioma idioma = repository.findById(id);
        idioma.setNome(dto.nome());
        idioma.setSigla(dto.sigla());
        
    }
    
}
