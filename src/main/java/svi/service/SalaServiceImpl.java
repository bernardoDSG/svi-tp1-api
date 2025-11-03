package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.SalaDTO;
import svi.model.Sala;
import svi.repository.SalaRepository;

@ApplicationScoped
public class SalaServiceImpl implements SalaService {
    @Inject
    SalaRepository repository;

    @Override
    @Transactional 
    public Sala create(SalaDTO dto) {
    Sala sala = new Sala();
    sala.setNome(dto.nome());


    if (dto.listaPoltronas() != null) {
        dto.listaPoltronas().forEach(poltrona -> poltrona.setSala(sala));
        sala.setListaPoltrona(dto.listaPoltronas());
    }

    repository.persist(sala);
    return sala;
}

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<Sala> findAll() {
        
        return repository.listAll();
    }

    @Override
    public Sala findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    public List<Sala> findByNome(String nome) {
        
        return repository.findByNome(nome);
    }
@Override
@Transactional 
public void update(Long id, SalaDTO dto) {
    Sala sala = repository.findById(id);
    sala.setNome(dto.nome());

    if (dto.listaPoltronas() != null) {
        dto.listaPoltronas().forEach(poltrona -> poltrona.setSala(sala));
        sala.setListaPoltrona(dto.listaPoltronas());
    }
}

    
}
