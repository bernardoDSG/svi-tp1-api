package svi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Sala extends DefaultEntity{
    private String nome;

    @OneToMany(mappedBy = "sala" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Poltrona> listaPoltrona;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Poltrona> getListaPoltrona() {
        return listaPoltrona;
    }

    public void setListaPoltrona(List<Poltrona> listaPoltrona) {
        this.listaPoltrona = listaPoltrona;
    }
    
}
