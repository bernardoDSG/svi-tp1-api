package svi.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.SessaoDTO;
import svi.dto.SessaoDTOResponse;
import svi.model.*;
import svi.repository.*;

@ApplicationScoped
public class SessaoServiceImpl implements SessaoService {

    @Inject
    SessaoRepository repository;

    @Inject
    FilmeRepository filmeRepository;

    @Inject
    SalaRepository salaRepository;

    @Inject
    IdiomaRepository idiomaRepository;

    @Override
    @Transactional
    public List<SessaoDTOResponse> findAll() {
        return repository.listAll().stream().map(s -> SessaoDTOResponse.valueOf(s)).toList();
    }

    @Override
    @Transactional
    public SessaoDTOResponse findById(Long id) {
        return SessaoDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public SessaoDTOResponse create(SessaoDTO dto) {
        Sessao sessao = new Sessao();
        sessao.setHorarioInicio(dto.horarioInicio());
        sessao.setHorarioFim(dto.horarioFim());
        sessao.setFilme(filmeRepository.findById(dto.idFilme()));
        sessao.setIdioma(idiomaRepository.findById(dto.idIdioma()));

        List<Sala> salas = new ArrayList<>();
        for (Long idSala : dto.idsSalas()) {
            salas.add(salaRepository.findById(idSala));
        }
        sessao.setSalas(salas);

        repository.persist(sessao);
        return SessaoDTOResponse.valueOf(sessao);
    }

    @Override
    @Transactional
    public void update(Long id, SessaoDTO dto) {
        Sessao sessao = repository.findById(id);
        sessao.setHorarioInicio(dto.horarioInicio());
        sessao.setHorarioFim(dto.horarioFim());
        sessao.setFilme(filmeRepository.findById(dto.idFilme()));
        sessao.setIdioma(idiomaRepository.findById(dto.idIdioma()));

        List<Sala> salas = new ArrayList<>();
        for (Long idSala : dto.idsSalas()) {
            salas.add(salaRepository.findById(idSala));
        }
        sessao.setSalas(salas);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
