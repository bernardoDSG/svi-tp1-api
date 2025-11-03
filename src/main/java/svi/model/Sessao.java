package svi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sessao extends DefaultEntity {

    private Boolean ingressosEsgotado;

    private LocalDateTime horarioInicio;

    private LocalDateTime horarioFim;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
    private List<Ingresso> ingressos;

    @ManyToOne
    @JoinColumn(name = "idioma_id")
    private Idioma idioma;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @ManyToMany
    @JoinTable(name = "sessao_sala",
        joinColumns = @JoinColumn(name = "sessao_id"),
        inverseJoinColumns = @JoinColumn(name = "sala_id"))
    private List<Sala> salas;

    // Getters e setters
    public Boolean getIngressosEsgotado() {
        return ingressosEsgotado;
    }

    public void setIngressosEsgotado(Boolean ingressosEsgotado) {
        this.ingressosEsgotado = ingressosEsgotado;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDateTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
}
