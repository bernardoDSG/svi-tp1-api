package svi.model;

import java.util.List;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import svi.converter.ConverterPremioString;

@Entity
public class Ator extends DefaultEntity{
    private String nome;

    @Convert(converter = ConverterPremioString.class)
    private List<Premio> premios;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> listaPremios) {
        this.premios = listaPremios;
    }
    
}
