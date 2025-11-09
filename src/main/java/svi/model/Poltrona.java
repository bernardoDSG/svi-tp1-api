package svi.model;
import jakarta.persistence.Entity;

@Entity
public class Poltrona extends DefaultEntity{
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

    
}
