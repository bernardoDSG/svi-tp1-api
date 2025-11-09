package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.converter.ConverterGeneroListString;
import svi.converter.ConverterPremioString;
import svi.dto.AtorDTO;
import svi.dto.FilmeDTO;
import svi.dto.FilmeDTOResponse;
import svi.dto.IdiomaDTO;
import svi.model.Filme;
import svi.model.Idioma;
import svi.repository.FilmeRepository;
import svi.repository.IdiomaRepository;


@ApplicationScoped
public class FilmeServiceImpl implements FilmeService{

    @Inject
    FilmeRepository repository;

    @Inject
    IdiomaRepository repositoryIdioma;
    ConverterGeneroListString converterGS = new ConverterGeneroListString();
    ConverterPremioString converterPS = new ConverterPremioString();
    

    @Override
    @Transactional
    public FilmeDTOResponse create(FilmeDTO dto) {
        
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(converterPS.convertToEntityAttribute(dto.premios()));
        filme.setListaGeneros(converterGS.convertToEntityAttribute(dto.generos()));
        filme.setListaAtores(dto.Atores().stream().map(a -> AtorDTO.fromDTO(a)).toList());
        Idioma idioma_original = IdiomaDTO.fromDTO(dto.idioma_original());
        filme.setIdioma_original(idioma_original);
        
        repository.persist(filme);
        return FilmeDTOResponse.valueOf(filme);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<FilmeDTOResponse> findAll() {
        
        return repository.listAll().stream()
                                   .map(f -> FilmeDTOResponse.valueOf(f))
                                   .toList();
    }

    @Override
    public List<FilmeDTOResponse> findByClassificacaoIndicativa(String classificacaoIndicativa) {
        
        return repository.findByClassificacaoIndicativa(classificacaoIndicativa).stream()
                                                                                .map(f -> FilmeDTOResponse.valueOf(f))
                                                                                .toList();
    }

    @Override
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
        Filme filme = repository.findById(id);
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(converterPS.convertToEntityAttribute(dto.premios()));
        filme.setListaGeneros(converterGS.convertToEntityAttribute(dto.generos()));
        filme.setListaAtores(dto.Atores().stream().map(a -> AtorDTO.fromDTO(a)).toList());
        Idioma idioma_original = IdiomaDTO.fromDTO(dto.idioma_original());
        filme.setIdioma_original(idioma_original);   
        
    }
    
}
