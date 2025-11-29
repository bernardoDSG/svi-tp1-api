package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.PoltronaDTO;
import svi.dto.SalaDTO;
import svi.dto.SalaDTOResponse;
import svi.model.Poltrona;
import svi.model.Sala;
import svi.repository.SalaRepository;

@ApplicationScoped
public class SalaServiceImpl implements SalaService {
    @Inject
    SalaRepository repository;

    @Override
    @Transactional 
    public SalaDTOResponse create(SalaDTO dto) {
    Sala sala = new Sala();
    sala.setNome(dto.nome());
    if(dto.poltronas() != null && dto.poltronas().isEmpty() == false) {
        List<Poltrona> listaPoltrona = dto.poltronas()
                                                .stream()
                                                .map(p ->PoltronaDTO.fromDTO(p))
                                                .toList();
        sala.setListaPoltrona(listaPoltrona); 
    } 
    repository.persist(sala);
    return SalaDTOResponse.valueOf(sala);
}

    @Override
    @Transactional 
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public List<SalaDTOResponse> findAll() {
        
        return repository.listAll().stream()
                                   .map(s -> SalaDTOResponse.valueOf(s))
                                   .toList();
    }

    @Override
    @Transactional
    public SalaDTOResponse findById(Long id) {
        
        return SalaDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public List<SalaDTOResponse> findByNome(String nome) {
        
        return repository.findByNome(nome).stream()
                                          .map(s -> SalaDTOResponse.valueOf(s))
                                          .toList();
    }
@Override
@Transactional 
public void update(Long id, SalaDTO dto) {
    Sala sala = repository.findById(id);
    sala.setNome(dto.nome());
    if(dto.poltronas() != null && dto.poltronas().isEmpty() == false) {
        sala.getListaPoltrona().clear();
        dto.poltronas().stream()
                       .map(p -> PoltronaDTO.fromDTO(p))
                       .forEach(p -> sala.getListaPoltrona().add(p));
                       
    }
}

    
}
