package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.converter.ConverterGeneroListString;
import svi.converter.ConverterIdObjeto;
import svi.converter.ConverterPremioString;

import svi.dto.FilmeDTO;
import svi.dto.FilmeDTOResponse;

import svi.model.Filme;

import svi.repository.AtorRepository;
import svi.repository.FilmeRepository;
import svi.repository.IdiomaRepository;


@ApplicationScoped
public class FilmeServiceImpl implements FilmeService{
    @Inject
    AtorRepository atorRepository;

    @Inject
    IdiomaRepository idiomaRepository;

    @Inject
    FilmeRepository repository;

    @Inject
    IdiomaRepository repositoryIdioma;
    ConverterGeneroListString converterGS = new ConverterGeneroListString();
    ConverterPremioString converterPS = new ConverterPremioString();
    

    @Override
    @Transactional
    public FilmeDTOResponse create(FilmeDTO dto) {
        ConverterIdObjeto converterId = new ConverterIdObjeto();
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(dto.idPremios().stream().map(i -> converterId.premioFromId(i)).toList());
        filme.setListaGeneros(dto.idGeneros().stream().map(i-> converterId.generoFromId(i)).toList());
        filme.setListaAtores(dto.idAtores().stream().map(i -> atorRepository.findById(i)).toList());
        filme.setIdioma_original(idiomaRepository.findById(dto.idIdiomaOriginal()));
        
        repository.persist(filme);
        return FilmeDTOResponse.valueOf(filme);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional
    public List<FilmeDTOResponse> findAll() {
        
        return repository.listAll().stream()
                                   .map(f -> FilmeDTOResponse.valueOf(f))
                                   .toList();
    }

    @Override
    @Transactional
    public List<FilmeDTOResponse> findByClassificacaoIndicativa(String classificacaoIndicativa) {
        
        return repository.findByClassificacaoIndicativa(classificacaoIndicativa).stream()
                                                                                .map(f -> FilmeDTOResponse.valueOf(f))
                                                                                .toList();
    }

    @Override
    @Transactional
    public FilmeDTOResponse findById(Long id) {
        
        return FilmeDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    public List<FilmeDTOResponse> findByTitulo(String titulo) {
        
        return repository.findByTitulo(titulo).stream()
                                              .map(f -> FilmeDTOResponse.valueOf(f))
                                              .toList();
    }

    @Override
    @Transactional
    public void update(Long id,FilmeDTO dto) {
        ConverterIdObjeto converterId = new ConverterIdObjeto();
        Filme filme = repository.findById(id);
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(dto.idPremios().stream().map(i -> converterId.premioFromId(i)).toList());
        filme.setListaGeneros(dto.idGeneros().stream().map(i-> converterId.generoFromId(i)).toList());
        filme.setListaAtores(dto.idAtores().stream().map(i -> atorRepository.findById(i)).toList());
        filme.setIdioma_original(idiomaRepository.findById(dto.idIdiomaOriginal()));   
        
    }
    
}
