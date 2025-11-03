package svi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Filme;

@ApplicationScoped
public class FilmeRepository implements PanacheRepository<Filme> {
    public List<Filme> findByTitulo(String titulo) {
        return find("SELECT f FROM Filme f  WHERE f.titulo LIKE ?1 " , "%" +titulo+"%").list();
    }

    public List<Filme> findByClassificacaoIndicativa(String classificacaoIndicativa) {
        return find("SELECT f FROM Filme f  WHERE f.classificacaoIndicativa LIKE ?1 " , "%" +classificacaoIndicativa+"%").list();
    }
}
