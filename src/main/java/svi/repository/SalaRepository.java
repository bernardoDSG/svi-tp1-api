package svi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Sala;

@ApplicationScoped
public class SalaRepository implements PanacheRepository<Sala>{
    public List<Sala> findByNome (String nome) {
        return find("SELECT s FROM Sala s  WHERE s.nome LIKE ?1 " , "%" +nome+"%").list();
    }
}
