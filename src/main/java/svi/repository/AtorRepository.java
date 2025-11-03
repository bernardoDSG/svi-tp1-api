package svi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Ator;

@ApplicationScoped
public class AtorRepository implements PanacheRepository<Ator>{
    
    public List<Ator> findByNome(String nome) {
        return find("SELECT a FROM Ator a  WHERE a.nome LIKE ?1 " , "%" +nome+"%").list();
    }
}
