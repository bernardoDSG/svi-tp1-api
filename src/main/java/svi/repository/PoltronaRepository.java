package svi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import svi.model.Poltrona;

@ApplicationScoped
public class PoltronaRepository implements PanacheRepository<Poltrona>{
    public List<Poltrona> findByNome(String nome) {
        return find("SELECT p FROM Poltrona p  WHERE p.nome LIKE ?1 " , "%" +nome+"%").list();
    }

    public List<Poltrona> findByDisponibilidade(Boolean estaOcupada) {
        return find("SELECT p FROM Poltrona p  WHERE p.estaOcupada = ?1 " ,estaOcupada).list();
    }
}

