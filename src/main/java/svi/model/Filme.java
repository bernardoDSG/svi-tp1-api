package svi.model;

import java.util.List;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import svi.converter.ConverterGeneroListString;
import svi.converter.ConverterPremioString;

@Entity
public class Filme extends DefaultEntity{
    private String titulo;
    private String duracao;
    private String sinopse;
    private String classificacaoIndicativa;
    @Convert(converter = ConverterPremioString.class)
    private List<Premio> listaPremios;
    @Convert(converter = ConverterGeneroListString.class)
    private List<Genero> listaGeneros;
    @ManyToMany
@JoinTable(
    name = "filme_atores_principais",
    joinColumns = @JoinColumn(name = "filme_id"),
    inverseJoinColumns = @JoinColumn(name = "ator_id")
)
    private List<Ator> listaAtores;
    @ManyToOne
    @JoinColumn(name = "idioma_id")
    private Idioma idioma_original; 




    
    public List<Premio> getListaPremios() {
        return listaPremios;
    }
    public void setListaPremios(List<Premio> listaPremios) {
        this.listaPremios = listaPremios;
    }
    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }
    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }
    public List<Ator> getListaAtores() {
        return listaAtores;
    }
    public void setListaAtores(List<Ator> listaAtores) {
        this.listaAtores = listaAtores;
    }
    public Idioma getIdioma_original() {
        return idioma_original;
    }
    public void setIdioma_original(Idioma idioma_original) {
        this.idioma_original = idioma_original;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    public String getSinopse() {
        return sinopse;
    }
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }
    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
    

}
