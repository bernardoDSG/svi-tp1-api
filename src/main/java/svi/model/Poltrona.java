package svi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Poltrona extends DefaultEntity{
    @ManyToOne
    @JoinColumn(name = "sala_id")
    @JsonBackReference
    private Sala sala;
    private String nome;
    private Boolean estaOcupada;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Boolean getEstaOcupada() {
        return estaOcupada;
    }
    public void setEstaOcupada(Boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }
     public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {   
        this.sala = sala;
    }

    
}
