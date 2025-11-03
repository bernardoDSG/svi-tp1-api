package svi.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import svi.dto.FilmeDTO;
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

    

    @Override
    @Transactional
    public Filme create(FilmeDTO dto) {
        Filme filme = new Filme();
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(dto.listaPremios());
        filme.setListaGeneros(dto.listaGeneros());
        filme.setListaAtores(dto.listaAtores());
        Idioma idioma_original = repositoryIdioma.findById(dto.idIdiomaOriginal());
        filme.setIdioma_original(idioma_original);
        
        repository.persist(filme);
        return filme;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

    @Override
    public List<Filme> findAll() {
        
        return repository.listAll();
    }

    @Override
    public List<Filme> findByClassificacaoIndicativa(String classificacaoIndicativa) {
        
        return repository.findByClassificacaoIndicativa(classificacaoIndicativa);
    }

    @Override
    public Filme findById(Long id) {
        
        return repository.findById(id);
    }

    @Override
    public List<Filme> findByTitulo(String titulo) {
        
        return repository.findByTitulo(titulo);
    }

    @Override
    @Transactional
    public void update(Long id,FilmeDTO dto) {
        Filme filme = repository.findById(id);
        filme.setTitulo(dto.titulo());
        filme.setDuracao(dto.duracao());
        filme.setSinopse(dto.sinopse());
        filme.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        filme.setListaPremios(dto.listaPremios());
        filme.setListaGeneros(dto.listaGeneros());
        filme.setListaAtores(dto.listaAtores());
        Idioma idioma_original = repositoryIdioma.findById(dto.idIdiomaOriginal());
        filme.setIdioma_original(idioma_original);   
        
    }
    
}
