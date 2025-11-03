package svi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class IngressoPromocional extends Ingresso{
    private Double desconto;
    private LocalDateTime inicioPromocao;
    private LocalDateTime fimPromocao;
    
    
    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    public LocalDateTime getInicioPromocao() {
        return inicioPromocao;
    }
    public void setInicioPromocao(LocalDateTime inicioPromocao) {
        this.inicioPromocao = inicioPromocao;
    }
    public LocalDateTime getFimPromocao() {
        return fimPromocao;
    }
    public void setFimPromocao(LocalDateTime fimPromocao) {
        this.fimPromocao = fimPromocao;
    }
    

}
