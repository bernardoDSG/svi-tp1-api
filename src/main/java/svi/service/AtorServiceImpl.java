package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.converter.ConverterIdObjeto;
import svi.converter.ConverterPremioString;
import svi.dto.AtorDTO;
import svi.dto.AtorDTOResponse;
import svi.model.Ator;
import svi.repository.AtorRepository;

@ApplicationScoped
public class AtorServiceImpl implements AtorService{
    @Inject
    AtorRepository repository;
    ConverterPremioString converterPS = new ConverterPremioString();
    ConverterIdObjeto converterId = new ConverterIdObjeto();
    @Override
    @Transactional
    public AtorDTOResponse create(AtorDTO dto) {
       
        Ator ator = new Ator();
        ator.setNome(dto.nome());
         ator.setPremios( dto.idPremios().stream().map(i ->converterId.premioFromId(i)).toList());

        repository.persist(ator);
        return AtorDTOResponse.valueOf(ator);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public List<AtorDTOResponse> findAll() {
        return repository.listAll()
                         .stream()
                         .map(m -> AtorDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional
    public AtorDTOResponse findById(Long id) {
        Ator ator = repository.findById(id);

        if(ator == null) {
            return null;
        }

        return AtorDTOResponse.valueOf(ator);

    }

    @Override
    @Transactional
    public List<AtorDTOResponse> findByNome(String nome) {
        
        return repository.findByNome(nome)
                         .stream()
                         .map(m -> AtorDTOResponse.valueOf(m))
                         .toList();
    }

    @Override
    @Transactional
    public void update(Long id, AtorDTO dto) {
        Ator ator = repository.findById(id);
        ator.setNome(dto.nome());
        ator.setPremios( dto.idPremios().stream().map(i ->converterId.premioFromId(i)).toList());
        
    }

  
}
