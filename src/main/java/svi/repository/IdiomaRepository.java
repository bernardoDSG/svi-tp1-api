package svi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Idioma;

@ApplicationScoped
public class IdiomaRepository implements PanacheRepository<Idioma>{
    public List<Idioma> findByNome(String nome){
        return find("SELECT i FROM Idioma i  WHERE i.nome LIKE ?1 " , "%" +nome+"%").list();
    }

    public List<Idioma> findBySigla(String sigla){
        return find("SELECT i FROM Idioma i  WHERE i.sigla LIKE ?1 " , "%" +sigla+"%").list();
    }
}